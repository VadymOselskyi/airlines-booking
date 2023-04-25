package io.skai.reservation.pl;

import com.kenshoo.jooq.AbstractDataTable;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class AirportTable extends AbstractDataTable<AirportTable> {

    public static final AirportTable TABLE = new AirportTable("airport");

    private AirportTable(String name) {
        super(name);
    }

    private AirportTable(AirportTable aliased, String alias) {
        super(aliased, alias);
    }

    public final TableField<Record, Long> id = createPKField("id", SQLDataType.BIGINT.identity(true));
    public final TableField<Record, String> name = createField(DSL.name("name"), SQLDataType.VARCHAR(255));
    public final TableField<Record, String> country_code = createField(DSL.name("country_code"), SQLDataType.VARCHAR.length(255).nullable(false));
    public final TableField<Record, String> city = createField(DSL.name("city"), SQLDataType.VARCHAR.length(255).nullable(false));

    @Override
    public AirportTable as(String alias) {
        return new AirportTable(this, alias);
    }
}