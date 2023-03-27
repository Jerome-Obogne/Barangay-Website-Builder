package com.caller;

import java.io.InputStream;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;


public class Aws_Operation {

	final static String acceskey="AKIAUQMCIFQ4KRA52VO4";                        
	final static String secretkey="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";   //generate a Secret key in IAM user
		
	public void aws3Upload(String bucketName, String fileName,String subdirectory, InputStream stream){
		
		
		BasicAWSCredentials awsCredentials=new BasicAWSCredentials(acceskey,secretkey);  // get the credentials to have an accces in amazon s3
		AmazonS3 s3client= AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion  // s3client
				   (Regions.US_EAST_1).build();
		
	
		  ObjectMetadata metadata = new ObjectMetadata();
		  PutObjectRequest uploadRequest = new PutObjectRequest(bucketName, subdirectory + fileName, stream, metadata).withCannedAcl(CannedAccessControlList.PublicRead);
		  
		  s3client.putObject(uploadRequest);
		  
		  
	}
	
	public void deleteObject(String bucketName, String keyname) {
		

		BasicAWSCredentials awsCredentials=new BasicAWSCredentials(acceskey,secretkey);  // get the credentials to have an accces in amazon s3
		AmazonS3 client= AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion  // s3client
				   (Regions.US_EAST_1).build();
		
		
		client.deleteObject(new DeleteObjectRequest(bucketName,"WebsiteBuilder/"+keyname));
		
		
	}
	
	
	
	
	
}
