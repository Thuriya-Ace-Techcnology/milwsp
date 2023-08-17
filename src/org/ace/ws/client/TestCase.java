package org.ace.ws.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ace.ws.model.premiumCal.ADO001;
import org.ace.ws.model.premiumCal.PRO001;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestCase {

	public static String webUrl = "http://localhost:8080/ggiwsp/ws";

	static Gson gson = new GsonBuilder().create();

	public static String getResponseString(Object object) {
		String responseString = gson.toJson(object);
		return responseString;
	}

	public static void main(String[] args) throws Exception {
		// String xmlString = "<name>aung</name><id>0001</id>";
		// JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
		// DTO dto = gson.fromJson(xmlJSONObj.toString(), DTO.class);
		//
		// System.out.println(dto.getId());
		// System.out.println(dto.getName());
		// System.out.println(xmlJSONObj);

		// String url = webUrl + URIConstants.PAYMENTSTATUS;
		// Transactions obj = new Transactions();
		// obj.setTransactionId("005");
		// obj.setDescription("005");
		// String str = HttpUtility.doWithUrl(url, obj);

		// String url = webUrl + URIConstants.GET_NOTIFICATION_LIST;
		// String str = HttpUtility.doWithUrl(url, null);

		// String url = webUrl + URIConstants.TWOC2P_PAYMENT;
		// String twoC2pResponse =
		// "paymentResponse=MIIGAQYJKoZIhvcNAQcDoIIF8jCCBe4CAQAxggG%2BMIIBugIBADCBoTCBkzELMAkGA1UEBhMCTU0xDzANBgNVBAgMBllhbmdvbjEPMA0GA1UEBwwGWWFuZ29uMSEwHwYDVQQKDBhHcmFuZCBHdWFyZGlhbiBJbnN1cmFuY2UxDDAKBgNVBAsMA0dHSTEMMAoGA1UEAwwDR0dJMSMwIQYJKoZIhvcNAQkBFhRnZ2kuaXQyMDE4QGdtYWlsLmNvbQIJAPLBdj7I2a1DMA0GCSqGSIb3DQEBAQUABIIBAHQ%2FomNwIbzvTUsX%2FPkAoBQ6kqxU0nkgetiWKfehvDe8vl%2BdZmhciUTA0psVfZXp7IxgAtR0Ml%2BMieKL3XAM21HbEFPN5ZMEinwnbM2Dc1NkyAMyQL4pw9b4sG6qsfc02pUoYM7pe80ag9oDCTMxtyOkPYyEZCIq0buhV4%2FEOE1wG1wWH1%2FmMYDjbXCIm7DIpu3O2eaTMLh8yeqCMrabwHgKcUqP5bcjQNJYXrtJxtXCoscbs26Io2qK0KOrHBlIIW5qm4vxGqYKOb%2BEdAqpqLudwVaa2n9%2FFva0VBmOvDrPi6RczRboh2ruVwucKGVFw7S0j89L4wji6VkeJtDgDvgwggQlBgkqhkiG9w0BBwEwFAYIKoZIhvcNAwcECCamGY1rV6sagIIEABO8axWIk5jcoUGkx7Xj%2FZkfCaRDFxpsLny%2BGRHY0sFtp2z3vniIbA0b5cd8sXpEYWvLXSrIF7YwXsfVuXGrpvwjzzyl8LIKqW6qkBMfV5tyVMLe9H75FXyDq%2FvVvCsD%2FbyhuT6UwsjIr1QQWu7R%2FuPyGJ81Sx2zfx3gGvBljbtPo2THoTVdhamrecSylTYYsGzqwnU9J10fAVp8Tgsxr1XU2XST9xL1b53gQzpLPWt5Q%2B5jynKt0HLimgURsvxVCc5qbw1z1QUjzycTqQ27F%2F2e9avqTMcmmBRcFzqMo5Yp8C5vkd85GU%2F2dcUEhois%2Bwu8iaPJJMmk1iKDITYt1T3J8qPilzGftiZiaC4jMaBb7s2gpUofPcKmLkUEmx%2Fk4gI9UHkOYDt2tehivkYFFFe8jEqwhAXolqa1nrL3rRFzWRkXTvMYpx2bKTubgkK79IkqKdoD%2B3gjB5nB%2FQxE4SHmYXraYSTkRJZNzX8miLSd4YHUQBOS8UqsCc6wv65h4aWoHCSbuaT5lVmy32UcDy9dN23eyltMXQDyqZhQ6owC72mSb3aUVwRdsdz6ADaL%2FDEVJvyWkxKeqd5fP92Xs%2FNHr%2Foj1bFnogrSsHlmo%2Bhwk63g0FgnIi4OSFO3RjzhgH4z3GPOtPasUfgJ2q%2FTy0TFKI6AXXEalQ%2BsMJNHYnEwPOMIHEBVifT53oKZol177Fnax9WufdGUvrJii8yMh6I0V8yMma16ioaXAG1irGhFPeqFeUsSFCkStQPkYmhanbOAhTsRiTKhvdzYDAGlXRKMKc5IIzFhlK4XMPBaqcl9KGi7jgg1lFg6cuPCW1HyueSMvURIceD2pnp8WKOYfj7i%2BtZLxMgoGuovbrGnyKZBt9W7Z9fayG2atnAKLD1nSLUC98eiKTZ6GTK949hj7zkPXui%2B1n4Bb9pbfY53BDUk%2FFuCos9Lure195Mc0n97X0F7p3BsnpCCpmK16yX3fiFf%2Fcg2EU4YupGMUW06XLI9vocls%2FWheQJx%2BCPlMC4rttiKrN6VyJqHOYVNJWdZk981eBgtoMqgsriaf3h2YRWaM09OtWpm72%2B0VXJPskUwVyFAMG5TXncyJMBjZynTfv%2BP59vMhygdQDrY1BvsSi0pVnXki%2FOd9n2X2T%2FGJBDtz%2BtKg4Ul4bNHX4FFjKtPQPGY7ODaIeotcjIcOM9R99X2ZWmuWX8xOv2cLVm70%2BWgW7SXffxHURrSmWXkUH0%2FpKRRlXYrFHQuYcZnWYHjuZ9So1mcO834Cf7Vublmr2ZxSAmFIIb5r%2Bb7Om8UFMo20JSj7LEy36aAFBLDznXRUXWdKubUw0SlVHrLYlpY%2BoT1XnMT2nz4DF4ToW5GNt3ajsg%3D";
		// String str = HttpUtility.doWithUrl(url, twoC2pResponse);

		// production response
		// String twoC2P001 =
		// "paymentResponse=MIIGIQYJKoZIhvcNAQcDoIIGEjCCBg4CAQAxggG%2BMIIBugIBADCBoTCBkzELMAkGA1UEBhMCTU0xDzANBgNVBAgMBllhbmdvbjEPMA0GA1UEBwwGWWFuZ29uMSEwHwYDVQQKDBhHcmFuZCBHdWFyZGlhbiBJbnN1cmFuY2UxDDAKBgNVBAsMA0dHSTEMMAoGA1UEAwwDR0dJMSMwIQYJKoZIhvcNAQkBFhRnZ2kuaXQyMDE4QGdtYWlsLmNvbQIJAPLBdj7I2a1DMA0GCSqGSIb3DQEBAQUABIIBAAxAeYocc40NP3S8OwZxlNMFvwxe1G1w6NpDpHBQwgrfdTXhV2%2BZzHyLEdf5wNDc%2BCfMp87cWL37KbnM5oP11ZDTN2V5%2F1wVs2HnC7IxfKFZtKXKuBlMx7Bdxy6krnPQFfifnFa12mjH5jWuZ4ngn9p7a5Eu9SNBgZyugS4Brr3HxGQ6yUCtzRTShzAb00pZ4%2BlLQF%2BVuyjaXy2tbju%2Fv6qT0F0HGd%2B7Lp%2Fl97W9alTWDz2kgsgS6VODdzg00ESkAzOmzxYs1YxKunBlCkUAyU6ZF4WWg1tGoLqfyIotqB2tKWbLykePrwt7VFkyaSAtXc8OuVUL7j7BPBcoWjjG8OMwggRFBgkqhkiG9w0BBwEwFAYIKoZIhvcNAwcECEmegwfZhdrIgIIEII7IDj4AFHTfwYGIKXYG%2F809a6KZEBvIi0CzifagiH3GaOmfG6UAGdOCl%2F9oHy9qGRNzr8%2BL55Wz59e1yXO%2F%2F%2Bm0zmHDBe%2FWfRN3ZRTbXr1uzjDr0A6od7gKAOqYcNrP95T570SyIrd5dr0WmXyD4P%2BFRSX0FiJqZbRE9mVsnKQ4pmxa2Q0OC6vKNj69rr9ZXsGimHYPRnnUE4%2B%2FriJNbz5mc9GNwUP6awo5pFkNF%2FFxg9VCPnnG%2FuorB8nEn%2F0%2FHE4O3PJiL57Jb7GNqLMr0TSryOQO%2Fr1s%2F3UfUoKpj9Ap17X6omwEvnbaIbe6ArgR25WlkPK67kmErexzhwuUhy6CEWFAp4OJoZymfR5o43%2FNl3qi962nFdfsGfro%2Fpep9o8cFRnuxG7Sqn9IiihFVimpvHCMq4f6%2BVpDTVvl85A20fwBYbxgCjs0%2BzMjHMiLGUmF5eWYK0xTG3O%2F7PQ3iRJhm79hDJcL%2FHFXO8Y%2BmDJQqNFkXH3GM7tRVYAc32vNELz05Zvr%2FFrT2i7frEcrNYslmi2ITlE4YZaKxZAEmOIPzNHAnjEmi1XoZc1DUjYuQ%2Bi6oBCw2llmpVYJbwQe1AP7eDGPcMbQN1wjuI5MnxTnoizuHrLJb66kB%2BbUFmpj8%2BRHCEJJVtAST7pU0qs6avtVTSJZheTN2uYBKv%2B9NaeCmQbDFoTcGAXwpZiAVfTddH7fI1jCIRWhjaWGCsQTTAlDA1tn2T8Yqqz9U8MNP6ahlzRF9QfHJqyd5C9mgx6vKoNU81cJJRZbn1m0nvnQSF%2BFz9ZpPWWWQQyMM08phd8UWVqC7mlZZZdI0L3kVZspfobKVoJuuIdqod%2BQub0eDbZtPmqxeYckKgGrE7vzuB%2BNbnJOyJVIqV2e2BrUtv2aMmzw81HT83a%2BjdsLL3%2BUCAZSnolD0sKdSOuWIjModcutQ13h4S%2F5wXUy0%2BWl6bDnVE9MvkZ0kB%2B8WN9HRBnS82HUXCg6dDTKOBRzo7ypk5MzYtER8HiTXCWdQ%2BaXLm0V6XH0vljUc8gF3jhMsYSOf5g9SiwOSebQWOVOPFSQvJNHgiwLVusayayGQsnsUpht%2B0Ov6aRu1pvIZaI3%2FN5eykS8pgvaEts6fLC896oB4MTmZOJdhZtV0wd%2B%2B08ySZs0uYqkTVcUfQulXN2ZPv%2Fe1Jm33s0L%2FqO6s8nMyY7NC9jBkDT2TsJ7qN3xwUsJrnul0zvTXj1iya3BDOyEzc2Jb2wlje%2F8Gra0kdeD4hv9NookqWrktSNXldMepfXW8p3TZuoB9DJgG6RXMhpA0DSL%2FKOJrYb%2FrngL5INwxhZJdESdK93U6NFFNmBbOkq3fyZpeMHTx0aC1AjTPq4aj4eJ10WRwhfqpGPlwbeTzDFMOoG3NPUuHaVz2OA2w%2ByQAjLk%2Fw%3D%3D";
		// uat response
		// String twoC2P001 =
		// "paymentResponse=MIIGEQYJKoZIhvcNAQcDoIIGAjCCBf4CAQAxggG%2BMIIBugIBADCBoTCBkzELMAkGA1UEBhMCTU0xDzANBgNVBAgMBllhbmdvbjEPMA0GA1UEBwwGWWFuZ29uMSEwHwYDVQQKDBhHcmFuZCBHdWFyZGlhbiBJbnN1cmFuY2UxDDAKBgNVBAsMA0dHSTEMMAoGA1UEAwwDR0dJMSMwIQYJKoZIhvcNAQkBFhRnZ2kuaXQyMDE4QGdtYWlsLmNvbQIJAPLBdj7I2a1DMA0GCSqGSIb3DQEBAQUABIIBACOnnNWwIt%2Fh2XdXcz4huVfUI1N0nc%2FtFvoyMP307CvxsjwgQz0DRjo9dnfLmup4nDguXicLQldlU1lTHvxE%2BHvuZG9WX%2BkSq2uwhgRrty7fPadR8R9Gqcc%2FyPBQ0ebi7tGNm0sXGXmmoYQhj3HIAdXOodSKdZReL30hHhWPc4QZDCBh8VMPYTEBpO%2Bcm506DqxrhaUXF7MIkj3uloC7N06XAamNIClh7ZeexivnoqOtwhNIp8q8pYNx9aOLy3Ai8qOr6sJsCOSVg2U8ojgvbq3%2FiRrmTWS%2FJVbgSI3XJ4wFeOVFlxng6jdUG83UaE5%2F%2Bz%2BmbHX%2B2IWWTjvINSxKTCkwggQ1BgkqhkiG9w0BBwEwFAYIKoZIhvcNAwcECBBs%2FFX6u16JgIIEEHBfB7ZafsrsJcEaA77l6zJE4ScNspIVFz%2FhKBXKu7UyRAU7d1xjetXEwXqhMRmY6Evt%2Bg9Wj18%2Fc9vds8rvmh88fHSHCJhx5tHVFKJzn4PJCIDy5m%2BIwF92U2qeAFMPoT6VDK0JOPzzrLFFv5iFlozl0z00CHXhNsh1unczwnVrB5Hh8bTuGANG1u5pgzuubxh6xM2szSninSrNQMhnIGhOwfK1fdEuyybEOQPw03gfyc%2BHsABeE8k3GGBoLmznTHyP%2BuaPKwplKhlKwZ1uGaTwveOblNaJF%2Bi8G3b6zKLn8YOiY%2FPaVHh7UpLwGmdoUHgvdYZLFPen9Qarm1haIBe%2BoiPi%2FDopJkfp135QvuqxiRddSQxmjU%2BdY2Yw%2Bdq%2BooqgOhuX0IX0VVW8r0jKaOSu69OexP91Jpx0BeS2mLI8nw8O%2BvZ7QBoqPh7P10aaXjavnSvMK0ku4QetT7gVsRWxHmOjnci9tsteYiwOcIZhxbp13MLLiERfanXlNj2LgQdfQ4bPXoX3i3%2F7UzW%2FwsybP76Jrl46gR5Qr3eZ1aasUdLKBVn0uAZH9ABltcGCDTlZ27O6nkjDusa2AjbP%2BvhmQmgayncG54UwNGHTQaeFJ7vtQ7g9Zuw4KswS7iRM6zFKT6pFfUEg0s5x3E1%2BiAdV%2BEXybh0wqeSI%2Bx6b628XG65rgyV06Ac54bLiNhAxDml33x24J5yZC7sHra%2BZLAXhn3sRoT4pgCrcuBHIogfu2Ny0f1Q75HDGV0sdzjdTVjnKV0scU8rtYmjSCkgouX8DjdoBdmBeIKtqh0xMBYGoehcVMTuKb0NCUjYhEoUw7PFBydacXoiNwzhiBnjzAMCrTTxB%2FDlruCK0CCmiUSIMTAfIMcNh3IGUSFXrwLh4KL0smTlar%2Fp%2B4dKxD8sKcxQEUNQYIibxTuLO0jWGGdAsmSonA2nz5fXnV0YDCE%2FDYWjJp%2BGi2H0ier65Rq1JsV51DXB9OTO6zR98ShE7N8JhMNR2erqgPHK7jK3FRNjiDU3qkbJsxW7o5naaboAQY%2Flpfh9ZN4bra5CS6KEayte%2B4PG%2FLA%2BEu4n45bFFLHXXDTpI8IjqSh1KmPVnxNJM2TyOSHxJF%2BV%2FiMRXb9ApYbqMNt%2F7V%2BeD5IFx6KtPDU2OYwVBp%2F%2B5F35tMgtl3qpAwv6sw%2BO0UlKT1mFPTxYVCTSnM87KE1sJMHoyWUJm0f4g7QlltN%2F1yNSzpgtZaTd%2B3Aw4juS7oKapIJWhkQ%2Fhim%2F%2FQEm6X7qDHuAcdRNFzSpqJTFouu7bGlHG5ue3FXcb%2BnjArtKADiM2kEvoyIua7MDM2kmLm2fgB9FDSRrxa9A%2B4fTMwWa92ZuTYbPyYsyP1rvpD0w3uYEBZGK4MtSk1Amf";
		// String twoC2P001 =
		// "paymentResponse=MIIGKQYJKoZIhvcNAQcDoIIGGjCCBhYCAQAxggG%2BMIIBugIBADCBoTCBkzELMAkGA1UEBhMCTU0xDzANBgNVBAgMBllhbmdvbjEPMA0GA1UEBwwGWWFuZ29uMSEwHwYDVQQKDBhHcmFuZCBHdWFyZGlhbiBJbnN1cmFuY2UxDDAKBgNVBAsMA0dHSTEMMAoGA1UEAwwDR0dJMSMwIQYJKoZIhvcNAQkBFhRnZ2kuaXQyMDE4QGdtYWlsLmNvbQIJAPLBdj7I2a1DMA0GCSqGSIb3DQEBAQUABIIBADIdywCz8x15jSkadt4n%2BWLL3D9ajmZ377wmSfj01pd2yT8usdO%2FtucXtgZRqXCUIWRah%2BSNC5MUepCtL6294wCLYMwoBJeFBhuWBtIWeV374zutVmCSk6T6%2Bt8MvVkIyiyf0SgLwq2dtT%2FT3kWCUZmbvB%2B%2BwiGZtpOFz2wUXhcel8QPyck46rxpGoRApGdiYPZX6l4gNnaRFroHmATJuziW8fK%2BMIj5htoHS6g1PVlQC1z%2F3cJ3IBMCUKO2qxcb8uCjYAfxjdjfS5UdVEaWtZ2aPfIvIS%2BlR%2BAXeRFiH19swwGNq2NT1VbLZ5v8gPkSBbViLXbwAA6HtcQX5%2B%2FWNSwwggRNBgkqhkiG9w0BBwEwFAYIKoZIhvcNAwcECGrqRG%2FuanRXgIIEKM5d9t%2FaR2q35LM%2BNTVns%2FJxsepaz3iG3IaEfnRJHlSLJgzP0LQ94TI1dX6G%2BRz2NkN5cbEv9QA3DpdI6pBJP50X90odkuVqkQE7BlIh1X%2FdKPN%2F3AkF%2FCTI0dPGvDkA2ZzBMjSusvI83bLTrfhYtzgX97FUfrHiGW7d0pL5Wj2SnpjJOMODOmin7AOwijmKQbpXP1jJCCBZ72C4MzmSb06VVi6TUxwIHIGWgApmLot%2B85lc7pp0MhVRh7pceUMa5WYnKUK2tbxHpB5O%2Fw%2BCqGTIzgAxrnB97aavALiFJEJZGysRHXx7Ts2PcjEFezJaLLZUv7W%2FgLoyx2P4vrCjEbcE4mx5qEONJr9uVidMYflhcTPxnuCfXyGGt2o9bHQlxNV%2BqmPYfIZ4crmfiAeqRH6gGpt9QhnDlFfb5HJBo25hj4axxIaX4jgEAH0OPjY%2FH2x82CjIk1a%2BfCDpXo86YMopBbMh7q9%2BBNzJapH%2BmST17Pd33CWLQKwoG%2BbV2MCO7KubezKw7wQakU6uDIDANT3iXn8kjkH6UcCD0UVX3v9XO9yK2eybprFhqRBmoaRmVzATAJ9VHO3v4xnvPvvvhO0L2r7O9vzzYKgHjdUm3QUfGqYY4kSFqPWDIi7hmmM7PM%2FIgNyB9Bqgx8qWPPUFg2GVz%2F1jpbrQiGEt%2FsjjsNtoNo8lOjJIIHsZmgF1x4sP6O9RYju5IIfdXxhlN9dS2SmG9HrcdGBJnQexRKE%2BrFicHiYbBYAiqf4Bz1Iesr%2Bv6j9fXuUuBecYCIGf9fiZHuRs2OoGSs7RHI%2BbgA5NYNX0sfaN%2FvygI4OLb2rF%2FKle2syW%2F3XY2ze3FGJOjRIorJI5ZunOhKOLdyL9EVcbSvdJVTdOwA5Uvj%2Bps9ah259NHkP6VQR9SfKnyP3ayqjFjPfItlIpLt8Yyb1pgpe0r2JmCyykmsA84XcJXriSaNz7XI39ymqbEB78BlXam6RhMjwvTAOJLLsQ3PaYKRxYz0DmowjIIYAWwpJswqSeJALPmT7cUE2Iq5Nk5GTNCTfwb%2FvnGlIVt0ZcT5KKkmwThrciplfcnzWpAX%2FQahTY12ybLHgyW7sRYbydnRk1S%2B6mLZI%2F%2BLFxQeuFqmzU8%2FPTXVA3Rp3GMwgF6FwR%2FjHv93znRiP94suIaodxNb%2FaJ16u4NRHFBd47CTeHdz45lzBXLcbuTheOrK9y%2Blb0q1c9TQ6bPHc1KEcBwsoGxqD%2FSrIHUsGekXnbXrLf7V92Xx%2FYTz1JJZTsrKpVaegreY8Ozya31KkMPy%2FmuEu55uxA9FKeQp6lHgp9EElREGOhzP9wbP6aQwWbsCT%2Fp2We313NvprCrvviLofDFKpHriGXlcaQc4K6WzuIlpUZaFi7GTIlVKcJObAokAf%2FO6pQ2ajZz9zlAvY1Q2aXngv";
		// twoC2P001 = twoC2P001.substring(16, twoC2P001.length());
		// String encrypted = java.net.URLDecoder.decode(twoC2P001, "UTF-8");
		// String xmlString = PKCS7.decrypt(Constants.getPrivateKey(),
		// Constants.getPrivateKeyPwd(), Base64.decode(encrypted),
		// Constants.getBksPassword());
		// JSONObject xmlJSONObj = XML.toJSONObject(xmlString);
		// System.out.println(xmlString);
		// TwoC2P001 twoC2P = gson.fromJson(xmlJSONObj.toString(),
		// TwoC2P001.class);

		// System.out.println("manage/user/signUp.xhtml".indexOf("signUp"));

		// String url = webUrl + URIConstants.OK$_PAYMENT;
		// String okDollarResponse =
		// "PayMentDet=zSYJOuSw%2FxSDMAi48BGmYu0GkmDn%2F5j1OzZ806XcLGxwCfn7v4Z5L8hHZjiyVk8pjlHsytI0MJIAk5oSGSRgVKTC6SxBMtDCkKRIuoXAZtb%2F4kCe72ZsTniPpRl9%2FqYiI64crjpBAXTJ5601offfPAmgb9aT6ygpxjbwldl2UL%2BZABCtpqFYO%2B3PZsb42HhHYq9UzStKo5FS0CYPm%2BGK9Q%2Bmtl%2B89OPU0FlYc9ADLjVieDidltNPoH4h%2BaPzGwHDBcih25cR7WQ%2Fn4LELsBxfJK8dK1iqJe%2FWVCkclh69E8Rqwm2dibvUJUb9qGlIh0vgRKp29d8FMuC6XfKOONu%2BGNQAWnPbpxoEe3keY4u4ssdQgz3OnE77FVFMGr3ygHS0zPyYgXCIJLhwZzRZZjxaOoEz14Mo69i38CpJOibh62%2FaJvzGKHI4WW9x8IKF5jauJvvG1vpJ0HEjA1nqaZYEn0%2BDxE6hvjBi1ecwMpe2vmGzZZf%2BJ6d4v4KMhAX4EU3IZsCjHfqM1vv%2FgKhDXKUe9gtqicdz41XoHiRreN6CRZHywv57i6uLThe0G2VCR2E5J4If7WljQMD7VfFe7PSWutfep%2F6WUHnIGVWFNnM7Us%3D%2CGGIP000000000002%2C00959768187249";
		// String str = HttpUtility.doWithUrl(url, okDollarResponse);

		// String url = webUrl + URIConstants.GET_NOTIFICATION_BY_DATE;
		// String str = HttpUtility.doWithUrl(url, "2018-07-12");

		// String url = webUrl + URIConstants.MOBILE_USER_LIST;
		// String str = HttpUtility.doWithUrl(url, null);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setEmail("it@gmail.com");
		// mobileUserDTO.setPassword("123456789");
		// SQA001 sqa001 = new SQA001();
		// sqa001.setSecurityQuestionId("ISSYS047002000000000224082018");
		// sqa001.setSecurityAnswer("Cat");
		// mobileUserDTO.addSQA001(sqa001);
		// SQA001 sqa0011 = new SQA001();
		// sqa0011.setSecurityQuestionId("ISSYS047002000000000124082018");
		// sqa0011.setSecurityAnswer("Black");
		// mobileUserDTO.addSQA001(sqa0011);
		// SQA001 sqa0012 = new SQA001();
		// sqa0012.setSecurityQuestionId("ISSYS047002000000000424082018");
		// sqa0012.setSecurityAnswer("Daw Oo");
		// mobileUserDTO.addSQA001(sqa0012);
		// String url = webUrl + URIConstants.REGISTER;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// String str = HttpUtility.requestMessage(mobileUserDTO);
		// System.out.println(str);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setEmail("yinyinkhine1993@gmail.com");
		// mobileUserDTO.setActivatedCode("458569");
		// String url = webUrl + URIConstants.ACTIVATE;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setId("INUSR005001000000008104062018");
		// SQA001 sqa1 = new SQA001("ISSYS047002000000004105062018", "Daw Hla");
		// SQA001 sqa2 = new SQA001("ISSYS047002000000004305062018", "Black");
		// SQA001 sqa3 = new SQA001("ISSYS047002000000004205062018", "Dog");
		// mobileUserDTO.addSQA001(sqa1);
		// mobileUserDTO.addSQA001(sqa2);
		// mobileUserDTO.addSQA001(sqa3);
		// String url = webUrl + URIConstants.SAVE_SECURITYANSWERS;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setEmail("thwethwe@gmail.com");
		// String url = webUrl + URIConstants.FORGET_PASSWORD;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setEmail("yinkhine@gmail.com");
		// SQA001 sqa1 = new SQA001("1", "Daw Aye");
		// SQA001 sqa2 = new SQA001("2", "White");
		// SQA001 sqa3 = new SQA001("3", "Dog");
		// mobileUserDTO.addSQA001(sqa1);
		// mobileUserDTO.addSQA001(sqa2);
		// mobileUserDTO.addSQA001(sqa3);
		// String url = webUrl + URIConstants.CHECK_SECURITYANSWERS;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setEmail("yinkhine@gmail.com");
		// mobileUserDTO.setNewPassword("yinyinkhine");
		// String url = webUrl + URIConstants.RESET_PASSWORD;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setEmail("yinyinkhine1993@gmail.com");
		// mobileUserDTO.setPassword("yinkhiddne");
		// String url = webUrl + URIConstants.LOGIN;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// FAQ001 mobileUserDTO = new FAQ001();
		// mobileUserDTO.setProductId("ISPRD003001000009446118072017");
		// String url = webUrl + URIConstants.GET_FAQ_LIST_BY_PRODUCT_ID;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// String url = webUrl + URIConstants.GET_SECURITYQUESTION_LIST;
		// String str = HttpUtility.doWithUrl(url, null);

		// String url = webUrl + URIConstants.PRODUCT_SPECIFICATION_LIST;
		// String str = HttpUtility.doWithUrl(url, null);

		// String url = webUrl + URIConstants.PRODUCT_FAQ_LIST;
		// String str = HttpUtility.doWithUrl(url, null);

		// MU001 mobileUserDTO = new MU001();
		// mobileUserDTO.setEmail("yinkhine14@gmail.com");
		// mobileUserDTO.setPassword("yinkhine");
		// mobileUserDTO.setNewPassword("yinyinkhine");
		// String url = webUrl + URIConstants.CHANGE_PASSWORD;
		// String str = HttpUtility.doWithUrl(url, mobileUserDTO);

		// MTP001 mobileTravelProposalDTO = new MTP001();
		// mobileTravelProposalDTO.setProductId("ISPRD003001000000032103082014");
		// mobileTravelProposalDTO.setUserId("INUSR005001000000008104062018");
		// MIP001 mobileInsuredPersonDTO = new MIP001();
		// Date date = new Date();
		// mobileInsuredPersonDTO.setFirstName("test");
		// mobileInsuredPersonDTO.setLastName("test");
		// mobileInsuredPersonDTO.setIdNo("11/tatata(N)010101");
		// mobileInsuredPersonDTO.setDepartureDate(Utils.getDateFormatString(date));
		// mobileInsuredPersonDTO.setArrivalDate(Utils.getDateFormatString(date));
		// mobileInsuredPersonDTO.setRoute("test");
		// mobileInsuredPersonDTO.setUnit(6);
		// mobileInsuredPersonDTO.setPremium(6000);
		// List<MIP001> insList = new ArrayList<MIP001>();
		// insList.add(mobileInsuredPersonDTO);
		// mobileTravelProposalDTO.setInsuredPersonList(insList);
		// System.out.println(getResponseString(mobileTravelProposalDTO));
		// String url = webUrl + URIConstants.INSERT_TRAVELPROPOSAL;
		// String str = HttpUtility.doWithUrl(url, mobileTravelProposalDTO);
		// System.out.println(str);

		// MTP001 mtp001 = new MTP001();
		// mtp001.setUserId("INUSR005001000000008104062018");
		// String url = webUrl + URIConstants.POLICY_LIST_BY_USER;
		// String str = HttpUtility.doWithUrl(url, mtp001);

		// Branch branch = new Branch();
		// branch.setName("TEST");
		// String url = webUrl + URIConstants.INSERT_BRANCH;
		// String str = HttpUtility.doWithUrl(url, branch);

		// System.out.println(str);

		// Calendar cal = Calendar.getInstance();
		// cal.setTime(new Date());
		// cal.add(Calendar.DATE, -1);
		// System.out.println(cal.getTime());

		// System.out.println(DateUtils.formatStringToDate("2018-07-12"));

		// MOTOR TEST
		// PRO001 pro001 = new PRO001();
		// pro001.setProductId("ISPRD0030001000000000129032013");
		// Map<String, String> keyFactorMap = new HashMap<String, String>();
		// keyFactorMap.put("ISSYS0130001000000000129032013", "5000000");
		// keyFactorMap.put("ISSYS0130001000000000329032013", "30");
		// keyFactorMap.put("ISSYS0130001000000000429032013", "1500");
		// pro001.setKeyFactorMap(keyFactorMap);
		// List<ADO001> ado001List = new ArrayList<ADO001>();
		// ADO001 addon1 = new ADO001("ISSYS0140001000000000229032013",
		// "100000");
		// ado001List.add(addon1);
		// pro001.setAddOnList(ado001List);
		// String url = webUrl + URIConstants.GET_PREMIUM;
		// String str = HttpUtility.doWithUrl(url, pro001);
		// System.out.println(getResponseString(pro001));
		// System.out.println(str);

		// FIRE TEST
		PRO001 pro001 = new PRO001();
		pro001.setProductId("ISPRD0030001000000001731032013");
		Map<String, String> keyFactorMap = new HashMap<String, String>();
		keyFactorMap.put("ISSYS0130001000000000129032013", "5000000");
		keyFactorMap.put("ISSYS0130001000000000531032013", "ISSYS0300001000000000131032013");
		keyFactorMap.put("ISSYS0130001000000000631032013", "ISSYS0110001000000000131032013");
		pro001.setKeyFactorMap(keyFactorMap);
		List<ADO001> ado001List = new ArrayList<ADO001>();
		ADO001 addon1 = new ADO001("ISSYS0140001000000000931032013", "");
		ADO001 addon2 = new ADO001("ISSYS0140001000000001931032013", "");
		// ADO001 addon1 = new ADO001("", "");
		ado001List.add(addon1);
		ado001List.add(addon2);
		pro001.setAddOnList(ado001List);

		PRO001 pro0011 = new PRO001();
		pro0011.setProductId("ISPRD0030001000000001731032013");
		Map<String, String> keyFactorMap1 = new HashMap<String, String>();
		keyFactorMap1.put("ISSYS0130001000000000129032013", "5000000");
		keyFactorMap1.put("ISSYS0130001000000000531032013", "ISSYS0300001000000000131032013");
		keyFactorMap1.put("ISSYS0130001000000000631032013", "ISSYS0110001000000000131032013");
		pro0011.setKeyFactorMap(keyFactorMap1);
		List<ADO001> ado001List1 = new ArrayList<ADO001>();
		ADO001 addon11 = new ADO001("ISSYS0140001000000000931032013", "");
		ADO001 addon21 = new ADO001("ISSYS0140001000000001931032013", "");
		// ADO001 addon1 = new ADO001("", "");
		ado001List1.add(addon11);
		ado001List1.add(addon21);
		pro0011.setAddOnList(ado001List1);

		List<PRO001> pro001List = new ArrayList<PRO001>();
		pro001List.add(pro001);
		pro001List.add(pro0011);

		String url = webUrl + URIConstants.GET_PREMIUM;
		String str = HttpUtility.doWithUrl(url, pro001List);

		System.out.println(getResponseString(pro001List));
		System.out.println(str);

		// PA TEST
		// PRO001 pro001 = new PRO001();
		// pro001.setProductId("ISPRD003001000009375222062017");
		// Map<String, String> keyFactorMap = new HashMap<String, String>();
		// keyFactorMap.put("ISSYS0130001000000000129032013", "5000000");
		// keyFactorMap.put("ISSYS013006000000241813032017",
		// "ISSYS0090001000000000229032013");
		// pro001.setKeyFactorMap(keyFactorMap);
		// List<ADO001> ado001List = new ArrayList<ADO001>();
		// // ADO001 addon1 = new ADO001("ISSYS0140001000000000931032013", "");
		// ADO001 addon1 = new ADO001("", "");
		// ado001List.add(addon1);
		// // pro001.setAddOnList(ado001List);
		// String url = webUrl + URIConstants.GET_PREMIUM;
		// String str = HttpUtility.doWithUrl(url, pro001);
		// System.out.println(getResponseString(pro001));
		// System.out.println(str);

		// System.out.println(Utils.getDateFormatString(new Date()));
		// System.out.println(Utils.getDateFormatString(null));

		// PaymentResponse response = new PaymentResponse();
		// response.setRespCode("f");
		// response.setStatus("99");
		//
		// if (response.getRespCode() == "f" && response.getStatus() == "99") {
		// System.out.println("TRUE");
		// } else {
		// System.err.println("FALSE");
		// }
	}
}
