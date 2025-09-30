package org.factoriaf5.happypaws.implementations;

import java.util.List;

public interface IGenericService<T, S> {

    public List<T> getEntities();

    public T storeEntity(S dto);

    public T showById(Long id);

}
