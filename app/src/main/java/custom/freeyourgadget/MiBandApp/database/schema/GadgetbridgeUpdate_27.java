/*  Copyright (C) 2017-2020 Andreas Shimokawa, protomors

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
package custom.freeyourgadget.MiBandApp.database.schema;

import android.database.sqlite.SQLiteDatabase;

import custom.freeyourgadget.MiBandApp.database.DBHelper;
import custom.freeyourgadget.MiBandApp.database.DBUpdateScript;
import custom.freeyourgadget.MiBandApp.entities.DeviceDao;

public class GadgetbridgeUpdate_27 implements DBUpdateScript {
    @Override
    public void upgradeSchema(SQLiteDatabase db) {
        if (!DBHelper.existsColumn(DeviceDao.TABLENAME, DeviceDao.Properties.Alias.columnName, db)) {
            String ADD_COLUMN_ALIAS = "ALTER TABLE " + DeviceDao.TABLENAME + " ADD COLUMN "
                    + DeviceDao.Properties.Alias.columnName + " TEXT";
            db.execSQL(ADD_COLUMN_ALIAS);
        }
    }

    @Override
    public void downgradeSchema(SQLiteDatabase db) {
    }
}
