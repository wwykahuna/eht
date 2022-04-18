package com.eht.evetrade.enums;

public enum SatelliteEnum {
    Hydrocarbons(16633, "烃类"),
    AtmosphericGases(16634, "标准大气"),
    EvaporiteDeposits(16635, "蒸发岩沉积物"),
    Silicates(16636, "硅酸盐"),
    Tungsten(16637, "钨"),
    Titanium(16638, "钛"),
    Scandium(16639, "钪"),
    Cobalt(16640, "钴"),
    Chromium(16641, "铬"),
    Vanadium(16642, "钒"),
    Cadmium(16643, "镉"),
    Platinum(16644, "铂"),
    Mercury(16646, "汞"),
    Caesium(16647, "铯"),
    Hafnium(16648, "铪"),
    Technetium(16649, "锝"),
    Dysprosium(16650, "镝"),
    Neodymium(16651, "钕"),
    Promethium(16652, "钷"),
    Thulium(16653, "铥"),

    HydrogenFB(4246,"氢燃料块"),
    HeliumFB(4247,"氦燃料块"),
    OxygenFB(4312,"氧燃料块"),
    NitrogenFB(4051,"氮燃料块"),

    Hydromagnetic(20171, "数据核心 - 磁流体物理"),
    Quantum(20414, "数据核心 - 量子物理"),
    Molecular(20415, "数据核心 - 分子工程"),
    Nanite(20416, "数据核心 - 纳米工程"),
    Electromagnetic(20417, "数据核心 - 电磁物理"),
    Electronic(20418, "数据核心 - 电子工程"),
    Graviton(20419, "数据核心 - 引力子物理"),
    Rocket(20420, "数据核心 - 火箭科学"),
    Mechanical(20424, "数据核心 - 机械工程");


    private final int id;
    private final String name;

    private SatelliteEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
