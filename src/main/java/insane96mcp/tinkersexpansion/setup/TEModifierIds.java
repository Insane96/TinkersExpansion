package insane96mcp.tinkersexpansion.setup;

import insane96mcp.tinkersexpansion.TinkersExpansion;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class TEModifierIds {

    //Combat Roll
    public static final ModifierId LONGFOOTED = id("longfooted");
    public static final ModifierId ACROBAT = id("acrobat");
    public static final ModifierId MULTI_ROLL = id("multi_roll");

    /**
     * Creates a new material ID
     * @param name  ID name
     * @return  Material ID object
     */
    private static ModifierId id(String name) {
        return new ModifierId(TinkersExpansion.MOD_ID, name);
    }
}
