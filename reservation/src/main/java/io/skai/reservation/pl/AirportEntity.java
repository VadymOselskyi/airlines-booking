package io.skai.reservation.pl;

import com.kenshoo.jooq.DataTable;
import com.kenshoo.pl.entity.AbstractEntityType;
import com.kenshoo.pl.entity.EntityField;
import com.kenshoo.pl.entity.annotation.Id;
import org.hibernate.annotations.Immutable;

public class AirportEntity extends AbstractEntityType<AirportEntity> {

    public static final AirportEntity INSTANCE = new AirportEntity();

    private AirportEntity() {
        super("airport");
    }

    @Override
    public DataTable getPrimaryTable() {
        return AirportTable.TABLE;
    }

    @Immutable
    @Id
    public static final EntityField<AirportEntity, Long> ID = INSTANCE.field(AirportTable.TABLE.id);

    public static final EntityField<AirportEntity, String> NAME = INSTANCE.field(AirportTable.TABLE.name);

    public static final EntityField<AirportEntity, String> COUNTRY_CODE = INSTANCE.field(AirportTable.TABLE.country_code);

    public static final EntityField<AirportEntity, String> CITY = INSTANCE.field(AirportTable.TABLE.city);
}