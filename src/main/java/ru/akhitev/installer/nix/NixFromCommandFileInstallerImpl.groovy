/*
 * ru.akhitev.installer is a library for installation programs to GNU Linux/UNIX systems.
 * Copyright (c) 2014 Aleksei Khitevi (Хитёв Алексей Юрьевич).
 *
 * This file is part of ru.akhitev.installer
 *
 * ru.akhitev.installer is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * ru.akhitev.installer is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
*/

package ru.akhitev.installer.nix

import ru.akhitev.net.ssh.ISshClient

import javafx.scene.control.TextArea;

class NixFromCommandFileInstallerImpl implements INixInstaller{
    ISshClient sshClient
    List commandList=[]
    TextArea guiLogger
    TextArea terminalArea
    StringBuilder fileNameBuilder = new StringBuilder()

    @Override
    boolean isSupported(String version, String subVersion, String applicationName) {
        if(isSupportedOperSystems(version, applicationName)){
            if(isSupportedSubVersion(version, subVersion, applicationName)){
                return true;
            }
        }
        return false
    }

    @Override
    void install() {
        for (command in commandList){
            guiLogger.appendText(command+"\r\n");
            terminalArea.appendText(command+"\r\n");
            if(((String)command).contains('sudo'))
                terminalArea.appendText(sshClient.executeSudoCommand(command)+"\r\n");
            else
                terminalArea.appendText(sshClient.executeCommand(command)+"\r\n")
        }
    }

    private boolean isSupportedOperSystems(String version, String applicationName){
        StringBuilder fileNameBuilder = new StringBuilder()
        fileNameBuilder.append("installers")
        fileNameBuilder.append(File.separator)
        fileNameBuilder.append("samba")
        def files = new File(fileNameBuilder.toString()).listFiles()
        for (file in files) {
            if (file.isDirectory())
                if(file.getName().toLowerCase().equals(version))
                    return true;
        }
        return false
    }

    private boolean isSupportedSubVersion(String version, String subVersion, String applicationName){
        fileNameBuilder.append(File.separator)
        fileNameBuilder.append(version)
        def files = new File(fileNameBuilder.toString()).listFiles()
        for (file in files) {
            if (!file.isDirectory()) {
                if(file.getName().toLowerCase().equals(subVersion+".cfg")){
                    setCommandList(file);
                    return true
                }
            }
        }
        return false
    }

    private setCommandList(File filePath){
        filePath.eachLine {	commandList<<it	}
    }
}