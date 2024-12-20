package controller;

public class ConversoesDados{
	
    public double bitNibble(int bits){
        return bits / 4;
    }
    
    public double nibbleBit(int nibbles){
        return nibbles * 4;
    }
    
    public double nibbleByte(int nibbles) {
    	return nibbles / 2;
    }
    
    public double byteNibble(int bytes) {
    	return bytes * 2;
    }
    
    public double bitByte(int bits){
        return bits / 8;
    } 
       
    public double byteBit(int bytes){
        return bytes * 8;
    }       
      
    public double bitKibibit(int bits){
        return bits / 1024;
    }  
      
    public double kibibitBits(int kibibits){
        return kibibits * 1024;
    }  
    
    public double bitKilobit(int bits){
        return bits / 1024;
    }  
      
    public double kilobitsBits(int kilobits){
        return kilobits * 1024;
    }  
    
    public double bitsKilobits(int bits) {
    	return bits / 1000;
    }
    
    public double quilobitsBits(int kilobits) {
    	return kilobits * 1000;
    }
      
    public double bitsKilobytes(int bits) {
    	return bits / 8000;
    }
    
    public double kilobytesBits(int kilobytes) {
    	return kilobytes * 8000;
    }
    
    public double bitsKibibytes(int bits) {
    	return bits / 8192;
    }
    
    public double kibibytesBits(int kibibytes) {
    	return kibibytes * 8192;
    }
    
    public double byteKilobytes(int bytes){
        return bytes / 1000;
    }
      
    public double kilobytesBytes(int kilobytes){
        return kilobytes * 1000;
    }
    
    public double byteKibibytes(int bytes){
        return bytes / 1024;
    }
      
    public double kibibytesBytes(int kibibytes){
        return kibibytes * 1024;
    }
      
    public double kilobytesMegabytes(int kilobytes){
        return kilobytes / 1000;
    }

    public double megabytesKilobytes(int megabytes){
        return megabytes * 1000;
    }
    
    public double kibibytesMebibytes(int kibibytes){
        return kibibytes / 1024;
    }

    public double mebibytesKilobytes(int mebibytes){
        return mebibytes * 1024;
    }

    public double kibibitMebibit(int kibibits){
        return kibibits / 1024;
    }  
      
    public double mebibitsKibibits(int mebibits){
        return mebibits * 1024;
    } 
    
    public double kilobitsMegabits(int kilobits){
        return kilobits / 1000;
    }

    public double megabitsKilobits(int megabits){
        return megabits * 1000;
    }

    public double megabytesGigabytes(int megabytes){
        return megabytes / 1000;
    }

    public double gigabytesMegabytes(int gigabytes){
        return gigabytes * 1000;
    }
    
    public double mebibytesGibibytes(int mebibytes){
        return mebibytes / 1024;
    }

    public double gibibytesMebibytes(int gibibytes){
        return gibibytes * 1024;
    }

    public double mebibitsGigabits(int mebibits){
        return mebibits / 1024;
    }  
      
    public double gibibitsMebibits(int gibibits){
        return gibibits * 1024;
    }  

    public double gigabitsMegabits(int gigabits){
        return gigabits * 1000;
    }
    
    
    public double gigabytesTerabytes(int gigabytes){
        return gigabytes / 1000;
    }

    public double terabytesGigabytes(int terabytes){
        return terabytes * 1000;
    }
    
    public double gibibytesTebibytes(int gibibytes){
        return gibibytes / 1000;
    }

    public double tebibytesGibibytes(int tebibytes){
        return tebibytes * 1000;
    }

    public double gibibitsTebibits(int gibibits){
        return gibibits / 1024;
    }  
      
    public double tebibitsGibibits(int tebibits){
        return tebibits * 1024;
    }  

    public double gigabitsTerabits(int gigabits){
        return gigabits / 1000;
    }

    public double terabitsGigabits(int terabits){
        return terabits * 1000;
    }

}