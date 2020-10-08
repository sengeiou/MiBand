/*  Copyright (C) 2016-2020 Andreas Shimokawa, Carsten Pfeiffer, Daniele
    Gobbetti, José Rebelo, Kranz, Sebastian Kranz

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
package custom.freeyourgadget.MiBandApp.devices.zetime;

import android.app.Activity;
import android.bluetooth.le.ScanFilter;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;

import custom.freeyourgadget.MiBandApp.R;
import custom.freeyourgadget.MiBandApp.devices.AbstractDeviceCoordinator;
import custom.freeyourgadget.MiBandApp.devices.InstallHandler;
import custom.freeyourgadget.MiBandApp.devices.SampleProvider;
import custom.freeyourgadget.MiBandApp.entities.DaoSession;
import custom.freeyourgadget.MiBandApp.entities.Device;
import custom.freeyourgadget.MiBandApp.impl.GBDevice;
import custom.freeyourgadget.MiBandApp.impl.GBDeviceCandidate;
import custom.freeyourgadget.MiBandApp.model.ActivitySample;
import custom.freeyourgadget.MiBandApp.model.DeviceType;


public class ZeTimeCoordinator extends AbstractDeviceCoordinator {
    @Override
    public DeviceType getDeviceType() {
        return DeviceType.ZETIME;
    }

    @NonNull
    @Override
    public Collection<? extends ScanFilter> createBLEScanFilters() {
        return super.createBLEScanFilters();
    }

    @NonNull
    @Override
    public DeviceType getSupportedType(GBDeviceCandidate candidate) {
        String name = candidate.getDevice().getName();
        if (name != null && name.startsWith("ZeTime")) {
            return DeviceType.ZETIME;
        }
        return DeviceType.UNKNOWN;
    }

    @Nullable
    @Override
    public Class<? extends Activity> getPairingActivity() {
        return null;
    }

    @Override
    public boolean supportsScreenshots() {
        return false;
    }

    @Override
    public int getAlarmSlotCount() {
        return 3; // FIXME - check the real value
    }

    @Override
    public boolean supportsWeather() {
        return true;
    }

    @Override
    public boolean supportsFindDevice() {
        return true;
    }

    @Override
    public InstallHandler findInstallHandler(Uri uri, Context context) {
        return null;
    }

    @Override
    public String getManufacturer() {
        return "MyKronoz";
    }

    @Override
    public boolean supportsActivityTracking() {
        return true;
    }

    @Override
    public Class<? extends Activity> getAppsManagementActivity() {
        return null;
    }

    @Override
    public boolean supportsHeartRateMeasurement(GBDevice device) {
        return true;
    }

    @Override
    public boolean supportsActivityDataFetching() {
        return true;
    }

    @Override
    public boolean supportsAppsManagement() {
        return false;
    }

    @Override
    public boolean supportsCalendarEvents() {
        return false;
    }

    @Override
    public SampleProvider<? extends ActivitySample> getSampleProvider(GBDevice device, DaoSession session) {
        return new ZeTimeSampleProvider(device, session);
    }

    @Override
    protected void deleteDevice(@NonNull GBDevice gbDevice, @NonNull Device device, @NonNull DaoSession session) {

    }

    @Override
    public boolean supportsSmartWakeup(GBDevice device) {
        return false;
    }

    @Override
    public boolean supportsRealtimeData() {
        return true;
    }

    @Override
    public boolean supportsMusicInfo() {
        return true;
    }

    @Override
    public int getBondingStyle() {
        return BONDING_STYLE_NONE;
    }

    @Override
    public boolean supportsUnicodeEmojis() { return  true; }

    @Override
    public int[] getSupportedDeviceSpecificSettings(GBDevice device) {
        return new int[]{
                R.xml.devicesettings_zetime,
                R.xml.devicesettings_timeformat,
                R.xml.devicesettings_wearlocation,
                R.xml.devicesettings_sync_calendar,
        };
    }
}
