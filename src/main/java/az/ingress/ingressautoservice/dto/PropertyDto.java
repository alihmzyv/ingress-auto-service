package az.ingress.ingressautoservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class PropertyDto<K, V> {
    public static <K, V> PropertyDto<K, V> of(K key, V val) {
        return new PropertyDto<>(key, val);
    }

    K key;
    V val;

    private PropertyDto(K key, V val) {
        this.key = key;
        this.val = val;
    }
}
