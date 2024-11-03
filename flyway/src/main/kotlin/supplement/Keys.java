/*
 * This file is generated by jOOQ.
 */
package supplement;


import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

import supplement.tables.FlywaySchemaHistory;
import supplement.tables.Items;
import supplement.tables.Supplements;
import supplement.tables.records.FlywaySchemaHistoryRecord;
import supplement.tables.records.ItemsRecord;
import supplement.tables.records.SupplementsRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, DSL.name("flyway_schema_history_pk"), new TableField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
    public static final UniqueKey<ItemsRecord> ITEMS_PKEY = Internal.createUniqueKey(Items.ITEMS, DSL.name("items_pkey"), new TableField[] { Items.ITEMS.ID }, true);
    public static final UniqueKey<SupplementsRecord> SUPPLEMENTS_NAME_KEY = Internal.createUniqueKey(Supplements.SUPPLEMENTS, DSL.name("supplements_name_key"), new TableField[] { Supplements.SUPPLEMENTS.NAME }, true);
    public static final UniqueKey<SupplementsRecord> SUPPLEMENTS_PKEY = Internal.createUniqueKey(Supplements.SUPPLEMENTS, DSL.name("supplements_pkey"), new TableField[] { Supplements.SUPPLEMENTS.ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ItemsRecord, SupplementsRecord> ITEMS__ITEMS_SUPPLEMENT_ID_FKEY = Internal.createForeignKey(Items.ITEMS, DSL.name("items_supplement_id_fkey"), new TableField[] { Items.ITEMS.SUPPLEMENT_ID }, Keys.SUPPLEMENTS_PKEY, new TableField[] { Supplements.SUPPLEMENTS.ID }, true);
}
