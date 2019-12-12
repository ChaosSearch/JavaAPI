/*
 * Copyright 2012-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance with
 * the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import java.util.logging.LogManager;
import java.io.IOException;
import java.util.logging.Level;
import java.util.Enumeration;

/**
 * <p>An AWS Request Signing Interceptor sample for arbitrary HTTP requests to an Amazon Elasticsearch Service domain.</p>
 * <p>The interceptor can also be used with the Elasticsearch REST clients for additional convenience and serialization.</p>
 * <p>Example usage with the Elasticsearch low-level REST client:</p>
 * <pre>
 * String serviceName = "es";
 * AWS4Signer signer = new AWS4Signer();
 * signer.setServiceName(serviceName);
 * signer.setRegionName("us-east-1");
 *
 * HttpRequestInterceptor interceptor =
 *     new AWSRequestSigningApacheInterceptor(serviceName, signer, credentialsProvider);
 *
 * return RestClient
 *     .builder(HttpHost.create("https://search-my-es-endpoint-gjhfgfhgfhg.us-east-1.amazonaws.com"))
 *     .setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor))
 *     .build();
 * </pre>
 * <p>Example usage with the Elasticsearch high-level REST client:</p>
 * <pre>
 * String serviceName = "es";
 * AWS4Signer signer = new AWS4Signer();
 * signer.setServiceName(serviceName);
 * signer.setRegionName("us-east-1");
 *
 * HttpRequestInterceptor interceptor =
 *     new AWSRequestSigningApacheInterceptor(serviceName, signer, credentialsProvider);
 * 
 * return new RestHighLevelClient(RestClient
 *     .builder(HttpHost.create("https://search-my-es-endpoint-gjhfgfhgfhg.us-east-1.amazonaws.com"))
 *     .setHttpClientConfigCallback(hacb -> hacb.addInterceptorLast(interceptor)));
 * </pre>
 */
public class AmazonElasticsearchServiceSample extends Sample {
    private static final String AES_ENDPOINT = "https://poc-trial.chaossearch.io/elastic/_msearch";
    public static void main(String[] args) throws IOException {
        AmazonElasticsearchServiceSample aesSample = new AmazonElasticsearchServiceSample();
        aesSample.queryCHAOS();
    }

    private void queryCHAOS() throws IOException {

String payload = "{\"index\":\"dev\",\"ignore_unavailable\":true\"preference\":1574355817948}\n{\"version\":true,\"size\":500,"\"sort\":[{\"_score\":{\"order\":\"desc\"}}],\"_source\":{\"excludes\":[]},\"stored_fields\":[\"*\"],\"script_fields\":{}," +"\"docvalue_fields\":[{\"field\":\"created\",\"format\":\"date_time\"}],"query\":{\"bool\":{\"must\":[{\"query_string\":{\"query\":\"cs_method:GET\",\"analyze_wildcard\":true,\"time_zone\":\"America/Chicago\"}}],\"filter\":[{\"match_all\":{}}],\"should\":[],\"must_not\":[]}},\"timeout\":\"30000ms\"}";

HttpPost httpPost = new HttpPost(AES_ENDPOINT );
        httpPost.setEntity(stringEntity(payload));
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.removeHeaders("accept-encoding");
        httpPost.removeHeaders("transfer-encoding");
        logRequest("es", httpPost);
    }
}
