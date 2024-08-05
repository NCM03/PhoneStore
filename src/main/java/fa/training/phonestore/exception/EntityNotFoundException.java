package fa.training.phonestore.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id,Class<?> entityClass) {
        super("Entity " + entityClass.getName() + " with id " + id + " not found");
    }


    }

