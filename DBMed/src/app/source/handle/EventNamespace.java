package handle;

public enum EventNamespace {
    REGISTRATION, DATABASE_CONNECT;

    public static EventNamespace getEventTagByName(String element){
     switch (element){
         case "DATABASE_CONNECT":
             return DATABASE_CONNECT;

         default:
             throw new EnumConstantNotPresentException(EventNamespace.class, element);
     }
    }
}
