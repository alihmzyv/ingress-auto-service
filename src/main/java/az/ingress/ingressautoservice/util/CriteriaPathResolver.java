package az.ingress.ingressautoservice.util;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;

public class CriteriaPathResolver {

    public static <T> Path<?> getPath(Root<T> root, String propertyPath) {
        String[] properties = propertyPath.split("\\.");
        Path<?> path = root;
        for (String property : properties) {
            path = path.get(property);
        }
        return path;
    }
}
