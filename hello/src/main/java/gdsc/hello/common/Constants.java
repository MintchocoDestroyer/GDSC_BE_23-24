package gdsc.hello.common;

public class Constants {
    public enum ExceptionClass{
        PRODUCT("Product");
        private String exceptionClass;

        ExceptionClass(String exceptionClass) {
            this.exceptionClass = exceptionClass;
        }

        public String getExceptionClass() {
            return exceptionClass;
        }

        @Override
        public String toString() {
            return "ExceptionClass{" +
                    "exceptionClass='" + getExceptionClass() + '\'' +
                    '}';
        }
    }
}
