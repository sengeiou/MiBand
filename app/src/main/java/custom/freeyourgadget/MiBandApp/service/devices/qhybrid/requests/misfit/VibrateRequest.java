/*  Copyright (C) 2019-2020 Daniel Dakhno

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
package custom.freeyourgadget.MiBandApp.service.devices.qhybrid.requests.misfit;


import java.nio.ByteBuffer;

import custom.freeyourgadget.MiBandApp.service.devices.qhybrid.requests.Request;

public class VibrateRequest extends Request {

    public VibrateRequest(boolean longVibration, short repeats, short millisBetween){
        ByteBuffer buffer = createBuffer();

        buffer.put(longVibration ? (byte)1 : 0);
        buffer.put((byte) repeats);
        buffer.putShort(millisBetween);
        this.data = buffer.array();
    }

    @Override
    public int getPayloadLength() {
        return 7;
    }

    @Override
    public byte[] getStartSequence() {
        return new byte[]{2, 15, 5};
    }
}
