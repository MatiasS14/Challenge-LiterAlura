package com.literalura.literalura.service;

public interface IDataConverter {
    public <T> T obtenerDatos(String json, Class<T> clase);
}
