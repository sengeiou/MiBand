/*  Copyright (C) 2015-2020 0nse, Andreas Böhler, Andreas Shimokawa,
    Carsten Pfeiffer, Cre3per, Daniel Dakhno, Daniele Gobbetti, Gordon Williams,
    Jean-François Greffier, João Paulo Barraca, José Rebelo, Kranz, ladbsoft,
    Manuel Ruß, maxirnilian, Pavel Elagin, protomors, Quallenauge, Sami Alaoui,
    Sophanimus, tiparega, Vadim Kaushan

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
package custom.freeyourgadget.MiBandApp.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import custom.freeyourgadget.MiBandApp.GBApplication;
import custom.freeyourgadget.MiBandApp.GBException;
import custom.freeyourgadget.MiBandApp.R;
import custom.freeyourgadget.MiBandApp.database.DBHandler;
import custom.freeyourgadget.MiBandApp.database.DBHelper;
import custom.freeyourgadget.MiBandApp.devices.DeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.UnknownDeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.banglejs.BangleJSCoordinator;
import custom.freeyourgadget.MiBandApp.devices.casiogb6900.CasioGB6900DeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.hplus.EXRIZUK8Coordinator;
import custom.freeyourgadget.MiBandApp.devices.hplus.HPlusCoordinator;
import custom.freeyourgadget.MiBandApp.devices.hplus.MakibesF68Coordinator;
import custom.freeyourgadget.MiBandApp.devices.hplus.Q8Coordinator;
import custom.freeyourgadget.MiBandApp.devices.hplus.SG2Coordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitbip.AmazfitBipCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitbip.AmazfitBipLiteCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitbips.AmazfitBipSCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitcor.AmazfitCorCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitcor2.AmazfitCor2Coordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitgtr.AmazfitGTRCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitgtr.AmazfitGTRLiteCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfitgts.AmazfitGTSCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.amazfittrex.AmazfitTRexCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.miband2.MiBand2Coordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.miband2.MiBand2HRXCoordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.miband3.MiBand3Coordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.miband4.MiBand4Coordinator;
import custom.freeyourgadget.MiBandApp.devices.huami.miband5.MiBand5Coordinator;
import custom.freeyourgadget.MiBandApp.devices.id115.ID115Coordinator;
import custom.freeyourgadget.MiBandApp.devices.itag.ITagCoordinator;
import custom.freeyourgadget.MiBandApp.devices.jyou.BFH16DeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.jyou.TeclastH30.TeclastH30Coordinator;
import custom.freeyourgadget.MiBandApp.devices.jyou.y5.Y5Coordinator;
import custom.freeyourgadget.MiBandApp.devices.lenovo.watchxplus.WatchXPlusDeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.liveview.LiveviewCoordinator;
import custom.freeyourgadget.MiBandApp.devices.makibeshr3.MakibesHR3Coordinator;
import custom.freeyourgadget.MiBandApp.devices.miband.MiBandConst;
import custom.freeyourgadget.MiBandApp.devices.miband.MiBandCoordinator;
import custom.freeyourgadget.MiBandApp.devices.mijia_lywsd02.MijiaLywsd02Coordinator;
import custom.freeyourgadget.MiBandApp.devices.miscale2.MiScale2DeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.no1f1.No1F1Coordinator;
import custom.freeyourgadget.MiBandApp.devices.pebble.PebbleCoordinator;
import custom.freeyourgadget.MiBandApp.devices.pinetime.PineTimeJFCoordinator;
import custom.freeyourgadget.MiBandApp.devices.qhybrid.QHybridCoordinator;
import custom.freeyourgadget.MiBandApp.devices.roidmi.Roidmi1Coordinator;
import custom.freeyourgadget.MiBandApp.devices.roidmi.Roidmi3Coordinator;
import custom.freeyourgadget.MiBandApp.devices.tlw64.TLW64Coordinator;
import custom.freeyourgadget.MiBandApp.devices.vibratissimo.VibratissimoCoordinator;
import custom.freeyourgadget.MiBandApp.devices.watch9.Watch9DeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.xwatch.XWatchCoordinator;
import custom.freeyourgadget.MiBandApp.devices.zetime.ZeTimeCoordinator;
import custom.freeyourgadget.MiBandApp.entities.Device;
import custom.freeyourgadget.MiBandApp.entities.DeviceAttributes;
import custom.freeyourgadget.MiBandApp.impl.GBDevice;
import custom.freeyourgadget.MiBandApp.impl.GBDeviceCandidate;
import custom.freeyourgadget.MiBandApp.model.DeviceType;

public class DeviceHelper {
    private static final Logger LOG = LoggerFactory.getLogger(DeviceHelper.class);

    private static final DeviceHelper instance = new DeviceHelper();
    // lazily created
    private List<DeviceCoordinator> coordinators;

    public static DeviceHelper getInstance() {
        return instance;
    }

    public DeviceType getSupportedType(GBDeviceCandidate candidate) {
        for (DeviceCoordinator coordinator : getAllCoordinators()) {
            DeviceType deviceType = coordinator.getSupportedType(candidate);
            if (deviceType.isSupported()) {
                return deviceType;
            }
        }
        return DeviceType.UNKNOWN;
    }

    public boolean getSupportedType(GBDevice device) {
        for (DeviceCoordinator coordinator : getAllCoordinators()) {
            if (coordinator.supports(device)) {
                return true;
            }
        }
        return false;
    }

    public GBDevice findAvailableDevice(String deviceAddress, Context context) {
        Set<GBDevice> availableDevices = getAvailableDevices(context);
        for (GBDevice availableDevice : availableDevices) {
            if (deviceAddress.equals(availableDevice.getAddress())) {
                return availableDevice;
            }
        }
        return null;
    }

    /**
     * Returns the list of all available devices that are supported by Gadgetbridge.
     * Note that no state is known about the returned devices. Even if one of those
     * devices is connected, it will report the default not-connected state.
     *
     * Clients interested in the "live" devices being managed should use the class
     * DeviceManager.
     * @param context
     * @return
     */
    public Set<GBDevice> getAvailableDevices(Context context) {
        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter == null) {
            GB.toast(context, context.getString(R.string.bluetooth_is_not_supported_), Toast.LENGTH_SHORT, GB.WARN);
        } else if (!btAdapter.isEnabled()) {
            GB.toast(context, context.getString(R.string.bluetooth_is_disabled_), Toast.LENGTH_SHORT, GB.WARN);
        }

        Set<GBDevice> availableDevices = new LinkedHashSet<>(getDatabaseDevices());
        Prefs prefs = GBApplication.getPrefs();
        String miAddress = prefs.getString(MiBandConst.PREF_MIBAND_ADDRESS, "");
        if (miAddress.length() > 0) {
            GBDevice miDevice = new GBDevice(miAddress, "MI", null, DeviceType.MIBAND);
            availableDevices.add(miDevice);
        }

        String pebbleEmuAddr = prefs.getString("pebble_emu_addr", "");
        String pebbleEmuPort = prefs.getString("pebble_emu_port", "");
        if (pebbleEmuAddr.length() >= 7 && pebbleEmuPort.length() > 0) {
            GBDevice pebbleEmuDevice = new GBDevice(pebbleEmuAddr + ":" + pebbleEmuPort, "Pebble qemu", "", DeviceType.PEBBLE);
            availableDevices.add(pebbleEmuDevice);
        }
        return availableDevices;
    }

    public GBDevice toSupportedDevice(BluetoothDevice device) {
        GBDeviceCandidate candidate = new GBDeviceCandidate(device, GBDevice.RSSI_UNKNOWN, device.getUuids());
        return toSupportedDevice(candidate);
    }

    public GBDevice toSupportedDevice(GBDeviceCandidate candidate) {
        for (DeviceCoordinator coordinator : getAllCoordinators()) {
            if (coordinator.supports(candidate)) {
                return coordinator.createDevice(candidate);
            }
        }
        return null;
    }

    public DeviceCoordinator getCoordinator(GBDeviceCandidate device) {
        synchronized (this) {
            for (DeviceCoordinator coord : getAllCoordinators()) {
                if (coord.supports(device)) {
                    return coord;
                }
            }
        }
        return new UnknownDeviceCoordinator();
    }

    public DeviceCoordinator getCoordinator(GBDevice device) {
        synchronized (this) {
            for (DeviceCoordinator coord : getAllCoordinators()) {
                if (coord.supports(device)) {
                    return coord;
                }
            }
        }
        return new UnknownDeviceCoordinator();
    }

    public synchronized List<DeviceCoordinator> getAllCoordinators() {
        if (coordinators == null) {
            coordinators = createCoordinators();
        }
        return coordinators;
    }

    private List<DeviceCoordinator> createCoordinators() {
        List<DeviceCoordinator> result = new ArrayList<>();
        result.add(new MiBand5Coordinator());
        result.add(new MiScale2DeviceCoordinator());
        result.add(new AmazfitBipCoordinator());
        result.add(new AmazfitBipLiteCoordinator());
        result.add(new AmazfitCorCoordinator());
        result.add(new AmazfitCor2Coordinator());
        result.add(new AmazfitGTRCoordinator());
        result.add(new AmazfitGTRLiteCoordinator());
        result.add(new AmazfitTRexCoordinator());
        result.add(new AmazfitGTSCoordinator());
        result.add(new AmazfitBipSCoordinator());
        result.add(new MiBand3Coordinator());
        result.add(new MiBand4Coordinator());
        result.add(new MiBand2HRXCoordinator());
        result.add(new MiBand2Coordinator()); // Note: MiBand2 and all of the above  must come before MiBand because detection is hacky, atm
        result.add(new MiBandCoordinator());
        result.add(new PebbleCoordinator());
        result.add(new VibratissimoCoordinator());
        result.add(new LiveviewCoordinator());
        result.add(new HPlusCoordinator());
        result.add(new No1F1Coordinator());
        result.add(new MakibesF68Coordinator());
        result.add(new Q8Coordinator());
        result.add(new EXRIZUK8Coordinator());
        result.add(new TeclastH30Coordinator());
        result.add(new XWatchCoordinator());
        result.add(new QHybridCoordinator());
        result.add(new ZeTimeCoordinator());
        result.add(new ID115Coordinator());
        result.add(new Watch9DeviceCoordinator());
        result.add(new WatchXPlusDeviceCoordinator());
        result.add(new Roidmi1Coordinator());
        result.add(new Roidmi3Coordinator());
        result.add(new Y5Coordinator());
        result.add(new CasioGB6900DeviceCoordinator());
        result.add(new BFH16DeviceCoordinator());
        result.add(new MijiaLywsd02Coordinator());
        result.add(new ITagCoordinator());
        result.add(new MakibesHR3Coordinator());
        result.add(new BangleJSCoordinator());
        result.add(new TLW64Coordinator());
        result.add(new PineTimeJFCoordinator());
        result.add(new SG2Coordinator());

        return result;
    }

    private List<GBDevice> getDatabaseDevices() {
        List<GBDevice> result = new ArrayList<>();
        try (DBHandler lockHandler = GBApplication.acquireDB()) {
            List<Device> activeDevices = DBHelper.getActiveDevices(lockHandler.getDaoSession());
            for (Device dbDevice : activeDevices) {
                GBDevice gbDevice = toGBDevice(dbDevice);
                if (gbDevice != null && DeviceHelper.getInstance().getSupportedType(gbDevice)) {
                    result.add(gbDevice);
                }
            }
            return result;

        } catch (Exception e) {
            GB.toast(GBApplication.getContext().getString(R.string.error_retrieving_devices_database), Toast.LENGTH_SHORT, GB.ERROR, e);
            return Collections.emptyList();
        }
    }

    /**
     * Converts a known device from the database to a GBDevice.
     * Note: The device might not be supported anymore, so callers should verify that.
     * @param dbDevice
     * @return
     */
    public GBDevice toGBDevice(Device dbDevice) {
        DeviceType deviceType = DeviceType.fromKey(dbDevice.getType());
        GBDevice gbDevice = new GBDevice(dbDevice.getIdentifier(), dbDevice.getName(), dbDevice.getAlias(), deviceType);
        List<DeviceAttributes> deviceAttributesList = dbDevice.getDeviceAttributesList();
        if (deviceAttributesList.size() > 0) {
            gbDevice.setModel(dbDevice.getModel());
            DeviceAttributes attrs = deviceAttributesList.get(0);
            gbDevice.setFirmwareVersion(attrs.getFirmwareVersion1());
            gbDevice.setFirmwareVersion2(attrs.getFirmwareVersion2());
            gbDevice.setVolatileAddress(attrs.getVolatileIdentifier());
        }

        return gbDevice;
    }

    /**
     * Attempts to removing the bonding with the given device. Returns true
     * if bonding was supposedly successful and false if anything went wrong
     * @param device
     * @return
     */
    public boolean removeBond(GBDevice device) throws GBException {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            BluetoothDevice remoteDevice = defaultAdapter.getRemoteDevice(device.getAddress());
            if (remoteDevice != null) {
                try {
                    Method method = BluetoothDevice.class.getMethod("removeBond", (Class[]) null);
                    Object result = method.invoke(remoteDevice, (Object[]) null);
                    return Boolean.TRUE.equals(result);
                } catch (Exception e) {
                    throw new GBException("Error removing bond to device: " + device, e);
                }
            }
        }
        return false;
    }

}
