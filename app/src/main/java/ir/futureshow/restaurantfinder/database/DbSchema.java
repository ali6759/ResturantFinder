package ir.futureshow.restaurantfinder.database;

public class DbSchema {
    public static final class UserTable {
        public static final String NAME = "course";

        public static final class Cols {
            public static final String USER_ID = "id";
            public static final String USER_NAME = "name";
            public static final String USER_EMAIL = "email";
            public static final String USER_PASSWORD = "credits";
        }
    }
    public static final class RestaurantTable {
        public static final String NAME = "restaurant";

        public static final class Cols {
            public static final String NAME = "name";
            public static final String RATE = "rate";
            public static final String ID = "id";
            public static final String FAV_STATUS = "fav_status";
            public static final String IMAGE1 = "image1";
            public static final String IMAGE2 = "image2";
            public static final String TYPE = "type";
            public static final String TEL = "tel";
            public static final String WEBSITE = "website";
            public static final String ADDRESS = "address";
        }
    }
}
