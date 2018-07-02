
package dates;

import java.text.*;
import java.util.*;

public class Dates 
{

   
    public static Scanner read = new Scanner(System.in);
    public static java.util.Date date = null;
    public static HashMap<String, String> southAfricanHolidays = new HashMap();
    public static String holidayKey = null;
    
    public static String changeDateFormat(String strDate, String format)
    {
     
            if(isValidDate(strDate))
            {
                return new SimpleDateFormat(format).format(date);
            }
            else
            {
                return "Invalid date";
            }
    }
    
    public static boolean checkHoliday(String strDate)
    {
        
        if(isValidDate(strDate))
        {
            if(southAfricanHolidays.containsKey(defaultFormat(strDate).substring(5)))
            {
                holidayKey = defaultFormat(strDate).substring(5);
                return true;
            }else{
                return false;
            }
        }
        else
        {
            System.out.println("Please enter a valid date");
            return false;
        }
    }
    
    public static String addBussDays(int days, String strDate){
        if(isValidDate(strDate))
        {
            date.setHours(days*24);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        else
        {
            return "Invalid date";
        }
    }
    
    public static String minusBussDays(int days, String strDate)
    {
        if(isValidDate(strDate))
        {
            date.setHours(-days*24);
            return new SimpleDateFormat("yyyy-MM-dd").format(date);
        }else
        {
            return "Invalid date";
        }
    }
    
    public static String showHoliday(String strDate)
    {
        
        if(isValidDate(strDate))
        {
            
            if(checkHoliday(strDate))
            {
                return "Date is a holiday: "+southAfricanHolidays.get(holidayKey);
            }
            else
            {
                if(new SimpleDateFormat("EEE").format(date).equals("Sat") || new SimpleDateFormat("EEE").format(date).equals("Sun"))
                {
                    return "Weekend";
                }
                else
                {
                    return "Business day";
                }            
            }
        }
        else
        {
            return "Date Invalid";
        }
    }
    
    public static boolean isValidDate(String strDate)
    {
        if((strDate.length() == 10 || strDate.length() == 8) && (strDate.contains("/") || strDate.contains("-")))
        {
           try
           {
               SimpleDateFormat sdf = null;
               String []split = {};
               
               if(strDate.contains("-"))
               {
                   
                    split = strDate.split("-");
                    sdf = new SimpleDateFormat("dd-MM-yyyy");
                    
               }
               else if(strDate.contains("/"))
               {
                   
                   split = strDate.split("/");
                   sdf = new SimpleDateFormat("dd/MM/yyyy");
               }
               
               int day  = Integer.parseInt(split[0]), month = Integer.parseInt(split[1]), year = Integer.parseInt(split[2]);
               
               if((day > 0 && day < 32) && (month > 0 && month < 13) && (split[2].length() == 4 && !split[2].startsWith("0") && year > 0))
               {
                   if((month == 1 && day < 32) || (month == 2 && day < 30) || (month == 3 && day < 32) || (month == 4 && day < 31) || (month == 5 && day < 32) || (month == 6 && day < 31) || (month == 7 && day < 32) || (month == 8 && day < 32) || (month == 9 && day < 31) || (month == 10 && day < 32) || (month == 11 && day < 31) || (month == 12 && day < 32)){
                        date = sdf.parse(strDate);
                        return true;
                   }
                   else
                   {
                      System.out.println("make sure the month you entered has a maximum number of days you entered");
                      return false;
                   }
                    
               }
               else
               {
                   System.out.println("The day must be greater than 0 and less than 32, the month must be greater than 0 and less than 13 and the year must be 4 digits long and greater than 0");
                   return false;
               }
               
           }catch(ParseException dtpe)
           {
               
               System.out.println("The date you entered does not match the format requested");
               return false;
               
           }catch(NumberFormatException nfe)
           {
               
               System.out.println("Make sure your date contains valid numbers");
               return false;
           }
        }
        else
        {
            System.out.println("Invalid date format");
            return false;
        }
    }
    
    
    public static String defaultFormat(String strDate)
    {
       
        if(isValidDate(strDate))
        {   
           return new SimpleDateFormat("yyyy-MM-dd").format(date);
           
        }
        else
        {
           return "Please enter the date in the requested format";
        }
       
    }
    
    public static void main(String[] args) throws ParseException{
              
        southAfricanHolidays.put("01-01", "New Year's Day");
        southAfricanHolidays.put("03-21", "Human Right's Day");
        southAfricanHolidays.put("03-25", "Good Friday");
        southAfricanHolidays.put("03-28", "Family Day");
        southAfricanHolidays.put("04-27", "Freedom Day");
        southAfricanHolidays.put("05-01", "Worker's Day");
        southAfricanHolidays.put("05-02", "Public Day");
        southAfricanHolidays.put("06-16", "Youth Day");
        southAfricanHolidays.put("08-09", "National Women's Day");
        southAfricanHolidays.put("09-24", "Heritage Day");
        southAfricanHolidays.put("12-16", "Day of Reconciliation");
        southAfricanHolidays.put("12-25", "Christmas Day");
        southAfricanHolidays.put("12-26", "Day of Goodwill");
        
        
        String line;
        System.out.println("Enter a date in the following format: day/month/year (dd/MM/yyyy)");
        
        while((line = read.next()) != null)
        {
            
            System.out.println(defaultFormat(line));
            System.out.println(showHoliday(line));
            System.out.println(addBussDays(7,line));
            
            System.out.println("Enter a date in the following format: day/month/year (dd/MM/yyyy)");
            
        }
        read.close();
        
    }
    
}
