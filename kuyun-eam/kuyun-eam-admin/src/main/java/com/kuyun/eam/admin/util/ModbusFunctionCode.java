/*
 * Copyright 2016 Kevin Herron
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kuyun.eam.admin.util;


public enum ModbusFunctionCode {

    ReadCoils(0x01, "01 读写"),
    ReadDiscreteInputs(0x02, "02 只读"),
    ReadHoldingRegisters(0x03, "03 读写"),
    ReadInputRegisters(0x04, "04 只读");
//    WriteSingleCoil(0x05, "05 Write Single Coil"),
//    WriteSingleRegister(0x06, "06 Write Single Register"),
//    ReadExceptionStatus(0x07, " Read Exception Status"),
//    Diagnostics(0x08),
//    GetCommEventCounter(0x0B),
//    GetCommEventLog(0x0C),
//    WriteMultipleCoils(0x0F),
//    WriteMultipleRegisters(0x10),
//    ReportSlaveId(0x11),
//    ReadFileRecord(0x14),
//    WriteFileRecord(0x15),
//    MaskWriteRegister(0x16),
//    ReadWriteMultipleRegisters(0x17),
//    ReadFifoQueue(0x18),
//    EncapsulatedInterfaceTransport(0x2B);

    private final int code;
    private final String name;

    ModbusFunctionCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(int code) {
        for (ModbusFunctionCode c : ModbusFunctionCode.values()) {
            if (c.getCode() == code) {
                return c.name;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName(){return name;}
}
