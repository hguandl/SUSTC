package com.ooad.scriptpro.service.docker;

import java.nio.ByteBuffer;

public class DockerLog {

    StringBuilder logs = new StringBuilder();

    public DockerLog(byte[] rawLog) {
        int pt = 0;
        while (pt < rawLog.length) {
            byte[] lengthBytes = {rawLog[pt + 4], rawLog[pt + 5], rawLog[pt + 6], rawLog[pt + 7]};
            ByteBuffer bb = ByteBuffer.wrap(lengthBytes);
            int length = bb.getInt();
            byte[] line = new byte[length];
            System.arraycopy(rawLog, pt + 8, line, 0, length);
            logs.append(new String(line));
            pt += 8 + length;
        }

    }

    @Override
    public String toString() {
        return logs.toString();
    }
}
