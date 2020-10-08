/*  Copyright (C) 2017-2020 Andreas Shimokawa, Carsten Pfeiffer, MyTimeKill

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
package custom.freeyourgadget.MiBandApp.service.devices.huami.amazfitbip;

import java.util.HashMap;
import java.util.Map;

import custom.freeyourgadget.MiBandApp.impl.GBDevice;
import custom.freeyourgadget.MiBandApp.model.DeviceType;
import custom.freeyourgadget.MiBandApp.service.devices.huami.HuamiFirmwareInfo;
import custom.freeyourgadget.MiBandApp.service.devices.huami.HuamiFirmwareType;
import custom.freeyourgadget.MiBandApp.util.ArrayUtils;

public class AmazfitBipFirmwareInfo extends HuamiFirmwareInfo {

    // this is the same as Cor
    public static final byte[] FW_HEADER = new byte[]{
            0x00, (byte) 0x98, 0x00, 0x20, (byte) 0xA5, 0x04, 0x00, 0x20, (byte) 0xAD, 0x04, 0x00, 0x20, (byte) 0xC5, 0x04, 0x00, 0x20
    };

    private static Map<Integer, String> crcToVersion = new HashMap<>();
    static {
        // firmware
        crcToVersion.put(25257, "0.0.8.74");
        crcToVersion.put(57724, "0.0.8.88");
        crcToVersion.put(27668, "0.0.8.96");
        crcToVersion.put(60173, "0.0.8.97");
        crcToVersion.put(3462,  "0.0.8.98");
        crcToVersion.put(55420, "0.0.9.14");
        crcToVersion.put(39465, "0.0.9.26");
        crcToVersion.put(27394, "0.0.9.40");
        crcToVersion.put(24736, "0.0.9.49");
        crcToVersion.put(49555, "0.0.9.59");
        crcToVersion.put(28586, "0.1.0.08");
        crcToVersion.put(26714, "0.1.0.11");
        crcToVersion.put(64160, "0.1.0.17");
        crcToVersion.put(21992, "0.1.0.26");
        crcToVersion.put(43028, "0.1.0.27");
        crcToVersion.put(59462, "0.1.0.33");
        crcToVersion.put(55277, "0.1.0.39");
        crcToVersion.put(47685, "0.1.0.43");
        crcToVersion.put(2839,  "0.1.0.44");
        crcToVersion.put(30229, "0.1.0.45");
        crcToVersion.put(24302, "0.1.0.70");
        crcToVersion.put(1333,  "0.1.0.80");
        crcToVersion.put(12017, "0.1.0.86");
        crcToVersion.put(8276,  "0.1.1.14");
        crcToVersion.put(5914,  "0.1.1.17");
        crcToVersion.put(6228,  "0.1.1.29");
        crcToVersion.put(44223, "0.1.1.31");
        crcToVersion.put(39726, "0.1.1.36");
        crcToVersion.put(11062, "0.1.1.39");
        crcToVersion.put(56670, "0.1.1.41");
        crcToVersion.put(58736, "0.1.1.45");
        crcToVersion.put(2602,  "1.0.2.00");
        crcToVersion.put(36157, "1.1.2.05");
        crcToVersion.put(26444, "1.1.5.02");
        crcToVersion.put(60002, "1.1.5.04");
        crcToVersion.put(5229,  "1.1.5.12");
        crcToVersion.put(32576, "1.1.5.16");
        crcToVersion.put(28893, "1.1.5.24");
        crcToVersion.put(61710, "1.1.5.56");
        crcToVersion.put(23387, "1.1.6.34");

        // Latin Firmware
        crcToVersion.put(52828, "1.1.5.36 (Latin)");
        crcToVersion.put(60625, "1.1.6.30 (Latin)");
        crcToVersion.put(17913, "1.1.6.32 (Latin)");

        // resources
        crcToVersion.put(12586, "0.0.8.74");
        crcToVersion.put(34068, "0.0.8.88");
        crcToVersion.put(59839, "0.0.8.96-98");
        crcToVersion.put(50401, "0.0.9.14-26");
        crcToVersion.put(22051, "0.0.9.40");
        crcToVersion.put(46233, "0.0.9.49-0.1.0.11");
        crcToVersion.put(12098, "0.1.0.17");
        crcToVersion.put(28696, "0.1.0.26-27");
        crcToVersion.put(5650,  "0.1.0.33");
        crcToVersion.put(16117, "0.1.0.39-45");
        crcToVersion.put(22506, "0.1.0.66-70");
        crcToVersion.put(42264, "0.1.0.77-80");
        crcToVersion.put(55934, "0.1.0.86-89");
        crcToVersion.put(26587, "0.1.1.14-25");
        crcToVersion.put(7446,  "0.1.1.29");
        crcToVersion.put(47887, "0.1.1.31-36");
        crcToVersion.put(14334, "0.1.1.39");
        crcToVersion.put(21109, "0.1.1.41");
        crcToVersion.put(23073, "0.1.1.45");
        crcToVersion.put(59245, "1.0.2.00");
        crcToVersion.put(20591, "1.1.2.05");
        crcToVersion.put(5341, "1.1.5.02-24");
        crcToVersion.put(22662, "1.1.5.36");
        crcToVersion.put(24045, "1.1.5.56");
        crcToVersion.put(37677, "1.1.6.30-32");
        crcToVersion.put(26735, "1.1.6.34");

        // gps
        crcToVersion.put(61520, "9367,8f79a91,0,0,");
        crcToVersion.put(8784,  "9565,dfbd8fa,0,0,");
        crcToVersion.put(16716, "9565,dfbd8faf42,0");
        crcToVersion.put(54154, "9567,8b05506,0,0,");
        crcToVersion.put(15717, "15974,e61dd16,126");
        crcToVersion.put(62532, "18344,eb2f43f,126");

        // font
        crcToVersion.put(61054, "8");
        crcToVersion.put(62291, "9 (old Latin)");
        crcToVersion.put(59577, "9 (Latin)");

        // BipOS FW
        crcToVersion.put(28373, "1.1.2.05 (BipOS 0.5)");
        crcToVersion.put(62977, "1.1.2.05 (BipOS 0.5.1)");

        // BipOS RES
        crcToVersion.put(16303, "1.1.2.05 (BipOS 0.5)");
        crcToVersion.put(61135, "1.1.2.05 (BipOS 0.5.1)");
    }

    public AmazfitBipFirmwareInfo(byte[] bytes) {
        super(bytes);
    }

    @Override
    protected HuamiFirmwareType determineFirmwareType(byte[] bytes) {
        if (ArrayUtils.startsWith(bytes, RES_HEADER) || ArrayUtils.startsWith(bytes, NEWRES_HEADER)) {
            if (bytes.length <= 100000) { // dont know how to distinguish from Cor/Mi Band 3 .res
                return HuamiFirmwareType.INVALID;
            }
            return HuamiFirmwareType.RES;
        }
        if (ArrayUtils.startsWith(bytes, GPS_ALMANAC_HEADER)) {
            return HuamiFirmwareType.GPS_ALMANAC;
        }
        if (ArrayUtils.startsWith(bytes, GPS_CEP_HEADER)) {
            return HuamiFirmwareType.GPS_CEP;
        }
        if (ArrayUtils.startsWith(bytes, FW_HEADER)) {
            if (searchString32BitAligned(bytes, "Amazfit Bip Watch")) {
                return HuamiFirmwareType.FIRMWARE;
            }
            return HuamiFirmwareType.INVALID;
        }
        if (ArrayUtils.startsWith(bytes, WATCHFACE_HEADER)) {
            return HuamiFirmwareType.WATCHFACE;
        }
        if (ArrayUtils.startsWith(bytes, NEWFT_HEADER)) {
            if (bytes[10] == 0x01) {
                return HuamiFirmwareType.FONT;
            } else if (bytes[10] == 0x02 || bytes[10] == 0x0A) {
                return HuamiFirmwareType.FONT_LATIN;
            }
        }
        for (byte[] gpsHeader : GPS_HEADERS) {
            if (ArrayUtils.startsWith(bytes, gpsHeader)) {
                return HuamiFirmwareType.GPS;
            }
        }

        return HuamiFirmwareType.INVALID;
    }

    @Override
    public boolean isGenerallyCompatibleWith(GBDevice device) {
        return isHeaderValid() && device.getType() == DeviceType.AMAZFITBIP;
    }

    @Override
    protected Map<Integer, String> getCrcMap() {
        return crcToVersion;
    }
}
