package departmentStore.Exeption;


    public  class InvalidPercentageException extends  Exception{
        public InvalidPercentageException (){
            super("percentage shude be a vale between 0 and 1");

        }

}
