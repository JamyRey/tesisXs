package org.tesis.xs.exception;

public enum MasterExceptionEnum {

    /**
     * En caso que no se pueda obtener un registro solicitado
     */
    recordNotFound      (1, "Registro no encontrado"),
    /**
     * En caso de que se detecte que ya hay un registro igual al enviado
     */
    recordAlreadyExists (2, "Registro existente"),
    /**
     * En caso de que ya exista otro registro con el mismo nombre
     */
    nameAlreadyExists   (3, "Nombre existente"),
    /**
     * En caso de que ya exista un registro con el mismo código
     */
    codeAlreadyExists   (4, "Código existente"),
    /**
     * En caso de que se detecte que el registro está siendo usado 
     */
    recordInUse         (5, "Registro en uso"),
    /**
     * En caso de que se detecte que el registro está inhabilidado 
     */
    recordDisable		(6, "Registro inhabilidado"),
    ;
    
    public final int    code;
    public final String message;
    
    
    private MasterExceptionEnum(int code, String message){
        this.code   = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
    /**
     * @return Propiedad de lenguaje correspondiente a la instancia actual
     */
    public String getLangProperty() {
        return "msg.exception."+this.toString();
    }
    
    /**
     * @return {@link MasterException} Excepción a lanzar
     */
    public MasterException exception() {
        return new MasterException(this);
    }
    
    /**
     * Devuelve la excepción a partir del código de error
     * @param Código del error
     * @return {@link MasterException} Excepción a lanzar
     */
    public static MasterException exception(int code) {
        return byCode(code).exception();
    }
    
    /**
     * Devuelve una entidad de excepción
     * @return {@link XExceptionEntity} con las constantes del enum
     */
    public ExceptionEntity entity() {
        return new ExceptionEntity(this.getCode(), this.getLangProperty(), this.getMessage());
    }
    
    /**
     * @param code Código buscado
     * @return El {@link MasterExceptionEnum} que corresponde a dicho código.
     */
    public static MasterExceptionEnum byCode(int code) {
        for (MasterExceptionEnum en : values())
            if (en.code == code)
                return en;
        throw new RuntimeException("Unknown XMasterExceptionEnum");
    }
    
    
}
