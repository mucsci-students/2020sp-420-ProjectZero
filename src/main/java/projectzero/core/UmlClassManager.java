package projectzero.core;

import projectzero.fx.Observable;
import projectzero.fx.Observer;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UmlClassManager implements Observable<UmlClass> {
    private Map<String, UmlClass> umlClassMap;
    private UmlClassYamlMapper umlClassYamlMapper;
    private ArrayList<Observer> observerList;
    private UmlClass changedUMLClass = null;

    public UmlClassManager() {
        this.umlClassMap = new HashMap<>();
        umlClassYamlMapper = new UmlClassYamlMapper();
        observerList = new ArrayList<Observer>();
    }

    public UmlClassManager(UmlClassYamlMapper umlClassYamlMapper) {
        this.umlClassMap = new HashMap<>();
        this.umlClassYamlMapper = umlClassYamlMapper;
        observerList = new ArrayList<Observer>();
    }

    public UmlClass addUmlClass(UmlClass umlClass) {
        UmlClass newUmlClass = umlClassMap.putIfAbsent(umlClass.getName(), umlClass);
        if(newUmlClass == null)
            changedUMLClass = umlClass;
        notifyObservers();
        return newUmlClass;
    }

    public UmlClass deleteUmlClass(String umlClassName) {
        UmlClass umlClass = umlClassMap.remove(umlClassName);
        changedUMLClass = umlClass;
        notifyObservers();
        return umlClass;
    }

    public UmlClass getUmlClass(String umlClassName) {
        return umlClassMap.get(umlClassName);
    }

    public UmlClass updateUmlClass(String umlClassName, UmlClass umlClass) {
        this.deleteUmlClass(umlClassName);
        UmlClass newUMLClass = this.addUmlClass(umlClass);
        return newUMLClass;
    }

    public List<UmlClass> listUmlClasses() {
        return new ArrayList<>(umlClassMap.values());
    }

    public void save(String path) throws IOException {
        umlClassYamlMapper.write(path, this.umlClassMap);
        notifyObservers();
    }

    public void load(String path) throws IOException {
        this.umlClassMap = umlClassYamlMapper.read(path);
        notifyObservers();
    }

    @Override
    public void register(Observer<UmlClass> observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer<UmlClass> observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer: observerList)
            observer.update(changedUMLClass);
        changedUMLClass = null;
    }
}

