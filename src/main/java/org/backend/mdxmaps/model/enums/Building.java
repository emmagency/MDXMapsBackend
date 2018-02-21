package org.backend.mdxmaps.model.enums;

/**
 * Created by Emmanuel Keboh on 21/02/2018.
 */
public enum Building {

    COLLEGE("College Building"),
    HATCHCROFT("Hatchcroft Building"),
    WILLIAMS("Williams Building"),
    SHEPPARDLIBRARY("Sheppard Library"),
    CIRCLE_CAFE("Circle Cafe"),
    BARN("Barn"),
    PORTAKABIN_A("Portakabin A"),
    PORTAKABIN_B("Portakabin B"),
    PORTAKABIN_A_EXT("Portakabin A_Ext"),
    PORTAKABIN_2("Portakabin 2"),
    PORTAKABIN_6_7("Portakabin 6 & 7"),
    PORTAKABIN_8("Portakabin 8"),
    MDXHOUSE("MDX House"),
    BUILDING9("Building 9"),
    BUILDING10("Building 10"),
    GROVE_BLOCK_A("Grove Block A"),
    GROVE_BLOCK_B("Grove Block B"),
    GROVE_BLOCK_C("Grove Block C"),
    VINE("Vine Building"),
    RAVENSFIELDS("Ravensfield House"),
    TOWNHALL("Townhall Building");

    private String value;

    Building(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
