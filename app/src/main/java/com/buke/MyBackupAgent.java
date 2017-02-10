package com.buke;

import android.app.backup.BackupAgentHelper;
import android.app.backup.BackupDataInput;
import android.app.backup.BackupDataOutput;
import android.os.ParcelFileDescriptor;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by buke on 16/3/21.
 */
public class MyBackupAgent extends BackupAgentHelper {

    public MyBackupAgent() {
        super();
    }

    @Override
    public void onBackup(ParcelFileDescriptor oldState, BackupDataOutput data,
            ParcelFileDescriptor newState) throws IOException {
        super.onBackup(oldState, data, newState);
    }

    @Override
    public void onRestore(BackupDataInput data, int appVersionCode, ParcelFileDescriptor newState)
            throws IOException {
        super.onRestore(data, appVersionCode, newState);
    }
}
