package com.simpledb.materialize;

import com.simpledb.query.*;

/**
 * The <i>count</i> aggregation function.
 * @author Edward Sciore
 */
public class CountFn implements AggregationFn {
   private String fldname;
   private int count;
   
   /**
    * Create a count aggregation function for the specified field.
    * @param fldname the name of the aggregated field
    */
   public CountFn(String fldname) {
      this.fldname = fldname;
   }
   
   /**
    * Start a new count.
    * Since SimpleDB does not support null values,
    * every record will be counted,
    * regardless of the field.
    * The current count is thus set to 1.
    * @see com.simpledb.materialize.AggregationFn#processFirst(com.simpledb.query.Scan)
    */
   public void processFirst(Scan s) {
      count = 1;
   }
   
   /**
    * Since SimpleDB does not support null values,
    * this method always increments the count,
    * regardless of the field.
    * @see com.simpledb.materialize.AggregationFn#processNext(com.simpledb.query.Scan)
    */
   public void processNext(Scan s) {
      count++;
   }
   
   /**
    * Return the field's name, prepended by "countof".
    * @see com.simpledb.materialize.AggregationFn#fieldName()
    */
   public String fieldName() {
      return "countof" + fldname;
   }
   
   /**
    * Return the current count.
    * @see com.simpledb.materialize.AggregationFn#value()
    */
   public Constant value() {
      return new Constant(count);
   }
}
