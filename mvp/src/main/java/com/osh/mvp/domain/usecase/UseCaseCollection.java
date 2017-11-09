package com.osh.mvp.domain.usecase;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * Created by olegshatava on 30.10.17.
 */

public class UseCaseCollection extends HashMap<String, Disposable> implements Disposable{

    public static class Builder{
        private Map<String, Disposable> items = new HashMap<>();

        public Builder add(String name, Disposable useCase){
            items.put(name, useCase);
            return this;
        }

        public Builder add(Disposable useCase){
            items.put(useCase.getClass().getSimpleName(), useCase);
            return this;
        }

        public UseCaseCollection build(){
            UseCaseCollection ret = new UseCaseCollection();
            ret.putAll(items);
            return ret;
        }
    }

    public static Builder builder(){
        return new Builder();
    }

    public <T> T get(String key){
        return (T)super.get(key);
    }

    public <T> T get(Class<T> key){
        return (T)super.get(key.getSimpleName());
    }

    @Override
    public void dispose() {
        for(Disposable disposable:this.values()){
            if(!disposable.isDisposed())
                disposable.dispose();
        }
    }

    @Override
    public boolean isDisposed() {
        for(Disposable disposable:this.values()){
            if(!disposable.isDisposed())
                return false;
        }
        return true;
    }

    protected void put(Disposable item) {
        put(item.getClass().getSimpleName(), item);
    }

}
