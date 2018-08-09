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
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.chinaxlt.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This sample demonstrates how to get started with basic requests to Aliyun OSS
 * using the OSS SDK for Java.
 */
public class UploadSample {

    private static String endpoint = "oss-cn-beijing-internal.aliyuncs.com";
    private static String accessKeyId = "LTAIxBfrqJZhZWsO";
    private static String accessKeySecret = "Py0X8ylvKDi8knM7WUqcUgm6tal8dd";
    private static String bucketName = "xhsdtest";
    private static String key = "image/4216773-fm.jpg";

    public static void main(String[] args) throws IOException {
        /*
         * Constructs a client instance with your account for accessing OSS
         */
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println("Getting Started with OSS SDK for Java\n");

        File img = new File("/Users/xianglingtao/Documents/MyWork/新华书店/images/4216773-fm.jpg");
        // 上传文件。
        System.out.println("start\n");
        PutObjectResult putObjectResult = ossClient.putObject(bucketName, key, new FileInputStream(img));
        System.out.println("end\n");

        putObjectResult.getRequestId();
        // 关闭OSSClient。
        ossClient.shutdown();

    }

}
