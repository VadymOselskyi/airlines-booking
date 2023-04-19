package io.skai.reservation.pl;

import com.kenshoo.jooq.AbstractDataTable;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;

public class AirportCreative extends AbstractDataTable<AirportCreative> {

    public static final AirportCreative TABLE = new AirportCreative("airport");

    private AirportCreative(String name) {
        super(name);
    }

    public final TableField<Record, Long> id = createPKField("id", SQLDataType.BIGINT.identity(true));
    public final TableField<Record, String> name = createField(DSL.name("name"), SQLDataType.VARCHAR(255), TABLE);
    public final TableField<Record, String> country_code = createField(DSL.name("country_code"), SQLDataType.VARCHAR.length(255).nullable(false));
    public final TableField<Record, String> city = createField(DSL.name("city"), SQLDataType.VARCHAR.length(255).nullable(false));

    @Override
    public AirportCreative as(String alias) {
        return new AirportCreative(alias);
    }
}