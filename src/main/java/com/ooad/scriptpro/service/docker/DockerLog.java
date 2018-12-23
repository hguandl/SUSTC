package com.ooad.scriptpro.service.docker;

import java.nio.ByteBuffer;

public class DockerLog {
    private StringBuilder stdOut = new StringBuilder();
    private StringBuilder stdErr = new StringBuilder();

    public DockerLog(byte[] rawLog) {
        int pt = 0;
        while (pt < rawLog.length) {
            byte type = rawLog[pt];
            byte[] lengthBytes = {rawLog[pt + 4], rawLog[pt + 5], rawLog[pt + 6], rawLog[pt + 7]};
            ByteBuffer bb = ByteBuffer.wrap(lengthBytes);
            int length = bb.getInt();
            byte[] line = new byte[length];
            System.arraycopy(rawLog, pt + 8, line, 0, length);
            getStream(type).append(new String(line));
            pt += 8 + length;
        }

    }

    private StringBuilder getStream(byte type) {
        switch (type) {
            case 1:
                return this.stdOut;
            case 2:
                return this.stdErr;
        }
        return null;
    }

    @Override
    public String toString() {
        return stdOut.toString();
    }

    public String getStdOut() {
        return stdOut.toString();
    }

    public String getStdErr() {
        return stdErr.toString();
    }
}
