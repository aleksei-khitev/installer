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

import java.awt.TextArea
import ru.akhitev.net.ssh.ISshClient

interface INixInstaller{
    boolean isSupported(String version, String subVersion, String applicationName)
    void install()
    void setGuiLogger(TextArea guiLogger)
    void setSshClient(ISshClient sshClient)
    ISshClient getSshClient()
    void setTerminalArea(TextArea terminalArea)
}