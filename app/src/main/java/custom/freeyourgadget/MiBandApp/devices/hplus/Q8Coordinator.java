/*  Copyright (C) 2017-2020 Daniele Gobbetti, João Paulo Barraca, tiparega

    This file is part of Gadgetbridge.

    Gadgetbridge is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Gadgetbridge is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package custom.freeyourgadget.MiBandApp.devices.hplus;

/*
* @author Alejandro Ladera Chamorro &lt;11555126+tiparega@users.noreply.github.com&gt;
*/


import androidx.annotation.NonNull;
import custom.freeyourgadget.MiBandApp.impl.GBDeviceCandidate;
import custom.freeyourgadget.MiBandApp.model.DeviceType;

/**
 * Pseudo Coordinator for the Q8, a sub type of the HPLUS devices
 */
public class Q8Coordinator extends HPlusCoordinator {

    @NonNull
    @Override
    public DeviceType getSupportedType(GBDeviceCandidate candidate) {
        String name = candidate.getDevice().getName();
        if(name != null && name.startsWith("Q8")){
            return DeviceType.Q8;
        }

        return DeviceType.UNKNOWN;
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Q8;
    }

    @Override
    public String getManufacturer() {
        return "Makibes";
    }

}
