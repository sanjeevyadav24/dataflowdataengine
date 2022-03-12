/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.analyticqa.amplitude;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO;
import org.apache.beam.sdk.io.gcp.bigquery.WriteResult;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;

import org.apache.beam.sdk.values.PCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.api.services.bigquery.model.TableFieldSchema;
import com.google.api.services.bigquery.model.TableRow;
import com.google.api.services.bigquery.model.TableSchema;
import com.google.gson.Gson;


/**
 * A  Beam programs for Amplitude API.
 *
 * <p>The example takes two strings, converts them to their upper-case
 * representation and logs them.
 *
 * <p>To run this starter example locally using DirectRunner, just
 * execute it without any additional parameters from your favorite development
 * environment.
 *
 * <p>To run this starter example using managed resource in Google Cloud
 * Platform, you should specify the following command-line options:
 *   --project=<YOUR_PROJECT_ID>
 *   --stagingLocation=<STAGING_LOCATION_IN_CLOUD_STORAGE>
 *   --runner=DataflowRunner
 */
public class StarterPipeline implements Serializable{
	
  /**
	 * 
	 */
  private static final long serialVersionUID = 1L;

  private static final Logger LOG = LoggerFactory.getLogger(StarterPipeline.class);
  
  private static final String STORAGE_BUCKET="";
  private static final String PROJECT_ID="";
  private static final String DATASET="amplitudeapi";
  private static final String TABLE_NAME="amplitudeapitable";
  
  
  public static void main(String[] args) throws InterruptedException, Exception {
		
    Pipeline p = Pipeline.create(
    PipelineOptionsFactory.fromArgs(args).withValidation().create());
    PCollection<String> lines = p.apply(TextIO.read().from(STORAGE_BUCKET));
          
    
    List<TableFieldSchema> fields = new ArrayList<TableFieldSchema>();
    fields.add(new TableFieldSchema().setName("device_id").setType("STRING"));
    fields.add(new TableFieldSchema().setName("user_id").setType("INTEGER"));
    fields.add(new TableFieldSchema().setName("client_event_time").setType("STRING"));
    fields.add(new TableFieldSchema().setName("event_id").setType("INTEGER"));
    fields.add(new TableFieldSchema().setName("session_id").setType("INTEGER"));
    fields.add(new TableFieldSchema().setName("event_type").setType("STRING"));
    fields.add(new TableFieldSchema().setName("platform").setType("STRING"));
    fields.add(new TableFieldSchema().setName("device_brand").setType("STRING"));
    fields.add(new TableFieldSchema().setName("device_manufacturer").setType("STRING"));
    fields.add(new TableFieldSchema().setName("device_family").setType("STRING"));
    fields.add(new TableFieldSchema().setName("location_lat").setType("FLOAT"));
    fields.add(new TableFieldSchema().setName("location_lng").setType("FLOAT"));
    //fields.add(new TableFieldSchema().setName("event_properties").setType("RECORD"));
    fields.add(new TableFieldSchema().setName("event_time").setType("STRING"));
    fields.add(new TableFieldSchema().setName("amplitude_id").setType("STRING"));
    
    
    
    
    TableSchema schema =  new TableSchema().setFields(fields);
       
    
    PCollection<TableRow> tabledata = lines.apply(ParDo.of(new ExtractJson())) ;
    WriteResult writeResult = tabledata.apply("Write message into BigQuery",
			BigQueryIO.writeTableRows()
			.to(PROJECT_ID + ":" + DATASET + "." + TABLE_NAME)
			.withSchema(schema)
			.withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_NEVER)
			.withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_APPEND)
			.withoutValidation()
			);

    p.run().waitUntilFinish();
  }
  
  static class ExtractJson extends DoFn<String, TableRow> implements Serializable{
	  
	  private static final long serialVersionUID = -12119381151250242L;
	  private static final Gson gsonObj=new Gson();

		@ProcessElement
		public void processElement(ProcessContext c) {
			
			String str = c.element();
			LOG.info(str);
			
		    SampleAPIObject amp = gsonObj.fromJson(str, SampleAPIObject.class);

			
		    TableRow row = new TableRow();
		    row.set("device_id", amp.getDevice_id());
		    row.set("user_id", amp.getUser_id());
		    row.set("client_event_time", amp.getClient_event_time());
		    row.set("event_id", amp.getEvent_id());
		    row.set("session_id", amp.getSession_id());
		    row.set("event_type", amp.getEvent_type());
		    row.set("platform", amp.getPlatform());
		    row.set("device_brand", amp.getDevice_brand());
		    row.set("device_manufacturer", amp.getDevice_manufacturer());
		    row.set("device_family", amp.getDevice_family());
		    row.set("location_lat", amp.getLocation_lat());
		    row.set("location_lng", amp.getLocation_lng());
		    //row.set("event_properties", amp.getEvent_properties());
		    row.set("event_time", amp.getEvent_time());
		    row.set("amplitude_id", amp.getAmplitude_id());
		    
		    
		    c.output(row);

	              		
		}
  }

}
