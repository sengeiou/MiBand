/*  Copyright (C) 2018-2020 Pavel Elagin

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
package custom.freeyourgadget.MiBandApp.devices.jyou;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import custom.freeyourgadget.MiBandApp.devices.AbstractSampleProvider;
import custom.freeyourgadget.MiBandApp.entities.DaoSession;
import custom.freeyourgadget.MiBandApp.entities.JYouActivitySample;
import custom.freeyourgadget.MiBandApp.entities.JYouActivitySampleDao;
import custom.freeyourgadget.MiBandApp.impl.GBDevice;

public class JYouSampleProvider extends AbstractSampleProvider<JYouActivitySample> {

    public static final int TYPE_ACTIVITY = -1;
    private final float movementDivisor = 6000.0f;
    private GBDevice mDevice;
    private DaoSession mSession;

    public JYouSampleProvider(GBDevice device, DaoSession session) {
        super(device, session);

        mSession = session;
        mDevice = device;
    }

    @Override
    public int normalizeType(int rawType) {
        return rawType;
    }

    @Override
    public int toRawActivityKind(int activityKind) {
        return activityKind;
    }

    @Override
    public float normalizeIntensity(int rawIntensity) {
        return rawIntensity/movementDivisor;
    }

    @Override
    public JYouActivitySample createActivitySample() {
        return new JYouActivitySample();
    }

    @Override
    public AbstractDao<JYouActivitySample, ?> getSampleDao() {
        return getSession().getJYouActivitySampleDao();
    }

    @Nullable
    @Override
    protected Property getRawKindSampleProperty() {
        return JYouActivitySampleDao.Properties.RawKind;
    }

    @NonNull
    @Override
    protected Property getTimestampSampleProperty() {
        return JYouActivitySampleDao.Properties.Timestamp;
    }

    @NonNull
    @Override
    protected Property getDeviceIdentifierSampleProperty() {
        return JYouActivitySampleDao.Properties.DeviceId;
    }
}
