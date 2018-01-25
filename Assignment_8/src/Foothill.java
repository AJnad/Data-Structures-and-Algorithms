/*
	AJAY NADHAVAJHALA
	CS 1C - Summer 2017 
	Professor David Harden
	Assignment 8 - Analyzing quickSort's Recursion Limits
 	This program investigates the recursion limits on quickSort.
 	It will test recursion limits between 2 and 300 by every 
 	two. It will time each recursion limits on different sized
 	arrays ranging from 20,000 to 1,100,000. It will then 
 	output those times onto the console. 
 */

import cs_1c.*;
import java.text.NumberFormat;
import java.util.*;

public class Foothill {
	public static void main(String[] args) {
		// different size arrays
		final int[] testArray = { 20000, 100000, 200000, 400000, 600000, 800000, 900000, 1000000, 1100000 };

		for (int x = 0; x < testArray.length; x++) {
			quickSort(testArray[x]);
		}
	}

	public static void quickSort(int arraySize) {
		int recursLimit, i, randNum;
		// for timing purposes
		double start, finish;
		Integer[] nums = new Integer[arraySize];
		Integer[] ordered = new Integer[arraySize];
		NumberFormat nf = NumberFormat.getInstance(Locale.US);
		nf.setMaximumFractionDigits(4);

		// fills the array up with random numbers
		for (i = 0; i < arraySize; i++) {
			randNum = (int) (Math.random() * arraySize);
			nums[i] = randNum;
		}

		System.out.println("Array Size = " + arraySize);
		System.out.println("Limit   |       Time");
		System.out.println("______________________");

		// the recursion limit starts from 2 and goes all the way to 300
		// it increments every 2
		for (recursLimit = 2; recursLimit < 302; recursLimit += 2) {
			for (i = 0; i < arraySize; i++) {
				ordered[i] = nums[i];
			}
			FHsort.setRecursionLimit(recursLimit);
			// times how long it takes
			start = System.nanoTime();
			FHsort.quickSort(ordered);
			finish = System.nanoTime();
			// displays the times according to the recursion limit it is on
			System.out.println(recursLimit + "	|	" + nf.format((finish - start) / 1e9));
		}
		System.out.print("----------------------");
		System.out.print("\n\n");
	}
}

/*** OUTPUT ***

Array Size = 20000
Limit   |       Time
______________________
2	|	0.0098
4	|	0.0095
6	|	0.0114
8	|	0.0102
10	|	0.0093
12	|	0.0076
14	|	0.0059
16	|	0.0072
18	|	0.0069
20	|	0.006
22	|	0.0038
24	|	0.0045
26	|	0.0039
28	|	0.0037
30	|	0.0038
32	|	0.0059
34	|	0.005
36	|	0.0037
38	|	0.0052
40	|	0.0049
42	|	0.0039
44	|	0.0036
46	|	0.0046
48	|	0.0038
50	|	0.004
52	|	0.0041
54	|	0.0063
56	|	0.0036
58	|	0.0041
60	|	0.0053
62	|	0.0038
64	|	0.0036
66	|	0.0058
68	|	0.0047
70	|	0.0057
72	|	0.0058
74	|	0.0057
76	|	0.0036
78	|	0.0036
80	|	0.0037
82	|	0.0041
84	|	0.0036
86	|	0.0036
88	|	0.0037
90	|	0.0037
92	|	0.0056
94	|	0.0057
96	|	0.0039
98	|	0.0037
100	|	0.0037
102	|	0.0037
104	|	0.0037
106	|	0.0038
108	|	0.006
110	|	0.0054
112	|	0.0038
114	|	0.0041
116	|	0.0041
118	|	0.004
120	|	0.0038
122	|	0.0037
124	|	0.0038
126	|	0.0037
128	|	0.0038
130	|	0.0038
132	|	0.0038
134	|	0.0038
136	|	0.0038
138	|	0.0038
140	|	0.0046
142	|	0.004
144	|	0.0038
146	|	0.0039
148	|	0.0039
150	|	0.0039
152	|	0.0039
154	|	0.0043
156	|	0.0039
158	|	0.0039
160	|	0.0039
162	|	0.004
164	|	0.0039
166	|	0.004
168	|	0.0064
170	|	0.0044
172	|	0.0039
174	|	0.0045
176	|	0.0075
178	|	0.0073
180	|	0.0076
182	|	0.0074
184	|	0.0074
186	|	0.0074
188	|	0.0078
190	|	0.0081
192	|	0.0074
194	|	0.0073
196	|	0.0073
198	|	0.0093
200	|	0.0075
202	|	0.0082
204	|	0.01
206	|	0.0127
208	|	0.0097
210	|	0.0074
212	|	0.0074
214	|	0.0074
216	|	0.0074
218	|	0.0075
220	|	0.0039
222	|	0.0038
224	|	0.0056
226	|	0.0037
228	|	0.0037
230	|	0.0038
232	|	0.0036
234	|	0.0036
236	|	0.0037
238	|	0.0038
240	|	0.0038
242	|	0.0038
244	|	0.0041
246	|	0.004
248	|	0.0041
250	|	0.004
252	|	0.0042
254	|	0.0041
256	|	0.0039
258	|	0.0039
260	|	0.0039
262	|	0.0041
264	|	0.0046
266	|	0.0039
268	|	0.0039
270	|	0.004
272	|	0.004
274	|	0.0042
276	|	0.0039
278	|	0.0041
280	|	0.004
282	|	0.0039
284	|	0.0039
286	|	0.0041
288	|	0.0041
290	|	0.004
292	|	0.0041
294	|	0.0041
296	|	0.004
298	|	0.0042
300	|	0.0041
----------------------

Array Size = 100000
Limit   |       Time
______________________
2	|	0.0166
4	|	0.0159
6	|	0.0159
8	|	0.0152
10	|	0.0153
12	|	0.015
14	|	0.0156
16	|	0.0154
18	|	0.0153
20	|	0.015
22	|	0.0148
24	|	0.0145
26	|	0.0365
28	|	0.034
30	|	0.0343
32	|	0.0259
34	|	0.02
36	|	0.0199
38	|	0.018
40	|	0.0151
42	|	0.0151
44	|	0.0148
46	|	0.0148
48	|	0.0147
50	|	0.0145
52	|	0.0154
54	|	0.015
56	|	0.0147
58	|	0.0149
60	|	0.0151
62	|	0.015
64	|	0.0151
66	|	0.0154
68	|	0.0153
70	|	0.0155
72	|	0.0162
74	|	0.0162
76	|	0.0158
78	|	0.0157
80	|	0.0156
82	|	0.0156
84	|	0.0155
86	|	0.0157
88	|	0.0168
90	|	0.0166
92	|	0.0162
94	|	0.0166
96	|	0.0158
98	|	0.0158
100	|	0.0167
102	|	0.0246
104	|	0.0266
106	|	0.0202
108	|	0.0165
110	|	0.0174
112	|	0.0172
114	|	0.0311
116	|	0.0247
118	|	0.023
120	|	0.0216
122	|	0.0175
124	|	0.0176
126	|	0.0178
128	|	0.0181
130	|	0.018
132	|	0.0182
134	|	0.0182
136	|	0.0201
138	|	0.0372
140	|	0.0299
142	|	0.0219
144	|	0.022
146	|	0.0219
148	|	0.0198
150	|	0.0186
152	|	0.0201
154	|	0.0184
156	|	0.0183
158	|	0.0183
160	|	0.0211
162	|	0.0393
164	|	0.0461
166	|	0.0312
168	|	0.0197
170	|	0.0192
172	|	0.0194
174	|	0.0428
176	|	0.0328
178	|	0.0261
180	|	0.0241
182	|	0.0206
184	|	0.0191
186	|	0.0192
188	|	0.0199
190	|	0.038
192	|	0.0265
194	|	0.0197
196	|	0.0199
198	|	0.0196
200	|	0.0211
202	|	0.0204
204	|	0.02
206	|	0.0206
208	|	0.0208
210	|	0.0309
212	|	0.0493
214	|	0.0377
216	|	0.0249
218	|	0.0213
220	|	0.0212
222	|	0.0383
224	|	0.0333
226	|	0.0476
228	|	0.0235
230	|	0.0219
232	|	0.0218
234	|	0.0216
236	|	0.0238
238	|	0.0218
240	|	0.0212
242	|	0.0212
244	|	0.0215
246	|	0.0235
248	|	0.0229
250	|	0.0228
252	|	0.0224
254	|	0.0228
256	|	0.0255
258	|	0.0514
260	|	0.0335
262	|	0.0236
264	|	0.0231
266	|	0.0236
268	|	0.0251
270	|	0.0231
272	|	0.044
274	|	0.0323
276	|	0.0481
278	|	0.0338
280	|	0.024
282	|	0.0243
284	|	0.0246
286	|	0.0261
288	|	0.0247
290	|	0.0349
292	|	0.0595
294	|	0.0422
296	|	0.0242
298	|	0.0259
300	|	0.024
----------------------

Array Size = 200000
Limit   |       Time
______________________
2	|	0.0372
4	|	0.0481
6	|	0.0655
8	|	0.0448
10	|	0.0353
12	|	0.035
14	|	0.0338
16	|	0.0337
18	|	0.0334
20	|	0.0332
22	|	0.0327
24	|	0.0337
26	|	0.0341
28	|	0.0354
30	|	0.0524
32	|	0.036
34	|	0.0343
36	|	0.0338
38	|	0.0346
40	|	0.0333
42	|	0.0336
44	|	0.0343
46	|	0.0333
48	|	0.0342
50	|	0.0395
52	|	0.0537
54	|	0.0353
56	|	0.0334
58	|	0.0341
60	|	0.0346
62	|	0.0364
64	|	0.0366
66	|	0.0456
68	|	0.0584
70	|	0.064
72	|	0.0381
74	|	0.0344
76	|	0.0353
78	|	0.0368
80	|	0.0361
82	|	0.0372
84	|	0.0582
86	|	0.0357
88	|	0.0359
90	|	0.0365
92	|	0.0368
94	|	0.043
96	|	0.0535
98	|	0.0359
100	|	0.0361
102	|	0.0355
104	|	0.0373
106	|	0.036
108	|	0.0363
110	|	0.0371
112	|	0.0367
114	|	0.0376
116	|	0.0369
118	|	0.0384
120	|	0.0392
122	|	0.0501
124	|	0.0755
126	|	0.0718
128	|	0.0406
130	|	0.0394
132	|	0.0392
134	|	0.0382
136	|	0.04
138	|	0.0394
140	|	0.0749
142	|	0.047
144	|	0.0452
146	|	0.0741
148	|	0.0418
150	|	0.0394
152	|	0.0422
154	|	0.0844
156	|	0.0537
158	|	0.0598
160	|	0.0626
162	|	0.0439
164	|	0.0408
166	|	0.0453
168	|	0.0438
170	|	0.044
172	|	0.0418
174	|	0.0424
176	|	0.0438
178	|	0.0428
180	|	0.0797
182	|	0.0797
184	|	0.0663
186	|	0.0437
188	|	0.0682
190	|	0.0433
192	|	0.0451
194	|	0.0448
196	|	0.0431
198	|	0.0439
200	|	0.0437
202	|	0.0439
204	|	0.0444
206	|	0.0447
208	|	0.0448
210	|	0.0841
212	|	0.0484
214	|	0.0463
216	|	0.0448
218	|	0.0457
220	|	0.0456
222	|	0.046
224	|	0.0457
226	|	0.0466
228	|	0.0456
230	|	0.0465
232	|	0.0461
234	|	0.0475
236	|	0.0464
238	|	0.0648
240	|	0.0555
242	|	0.0485
244	|	0.0494
246	|	0.0508
248	|	0.0491
250	|	0.0493
252	|	0.0492
254	|	0.0502
256	|	0.0498
258	|	0.0507
260	|	0.0508
262	|	0.0485
264	|	0.0724
266	|	0.1
268	|	0.0636
270	|	0.0504
272	|	0.05
274	|	0.0491
276	|	0.0865
278	|	0.0694
280	|	0.0531
282	|	0.1208
284	|	0.1176
286	|	0.1167
288	|	0.0733
290	|	0.0811
292	|	0.0524
294	|	0.0533
296	|	0.0534
298	|	0.0528
300	|	0.0538
----------------------

Array Size = 400000
Limit   |       Time
______________________
2	|	0.0855
4	|	0.0808
6	|	0.0849
8	|	0.1221
10	|	0.1022
12	|	0.0773
14	|	0.0775
16	|	0.0766
18	|	0.1132
20	|	0.0819
22	|	0.1169
24	|	0.0833
26	|	0.0761
28	|	0.076
30	|	0.076
32	|	0.0975
34	|	0.1093
36	|	0.0756
38	|	0.0766
40	|	0.0757
42	|	0.1123
44	|	0.1076
46	|	0.1142
48	|	0.0763
50	|	0.0765
52	|	0.0768
54	|	0.0795
56	|	0.0766
58	|	0.0764
60	|	0.0797
62	|	0.1136
64	|	0.0817
66	|	0.0801
68	|	0.0764
70	|	0.0773
72	|	0.0793
74	|	0.0789
76	|	0.0809
78	|	0.0814
80	|	0.079
82	|	0.0801
84	|	0.0803
86	|	0.0804
88	|	0.0799
90	|	0.0818
92	|	0.1206
94	|	0.1351
96	|	0.084
98	|	0.0902
100	|	0.1273
102	|	0.0986
104	|	0.0958
106	|	0.085
108	|	0.0854
110	|	0.0849
112	|	0.0859
114	|	0.0858
116	|	0.0899
118	|	0.0865
120	|	0.0881
122	|	0.094
124	|	0.09
126	|	0.0866
128	|	0.0882
130	|	0.0907
132	|	0.0967
134	|	0.1179
136	|	0.0911
138	|	0.1436
140	|	0.1165
142	|	0.1108
144	|	0.1113
146	|	0.096
148	|	0.0899
150	|	0.129
152	|	0.114
154	|	0.1262
156	|	0.0963
158	|	0.0924
160	|	0.0938
162	|	0.1541
164	|	0.1592
166	|	0.0989
168	|	0.0953
170	|	0.1138
172	|	0.1253
174	|	0.0958
176	|	0.1515
178	|	0.1456
180	|	0.0958
182	|	0.0962
184	|	0.1137
186	|	0.1637
188	|	0.0973
190	|	0.1069
192	|	0.1928
194	|	0.1007
196	|	0.0997
198	|	0.099
200	|	0.0997
202	|	0.0964
204	|	0.101
206	|	0.1038
208	|	0.0997
210	|	0.1324
212	|	0.1028
214	|	0.1005
216	|	0.0988
218	|	0.1205
220	|	0.1048
222	|	0.1026
224	|	0.1216
226	|	0.1014
228	|	0.1014
230	|	0.1036
232	|	0.1346
234	|	0.1271
236	|	0.1288
238	|	0.1037
240	|	0.1292
242	|	0.1454
244	|	0.1037
246	|	0.1056
248	|	0.1308
250	|	0.1329
252	|	0.1425
254	|	0.1074
256	|	0.1086
258	|	0.1104
260	|	0.1094
262	|	0.1076
264	|	0.1095
266	|	0.1064
268	|	0.1076
270	|	0.1087
272	|	0.1122
274	|	0.1125
276	|	0.1168
278	|	0.1768
280	|	0.115
282	|	0.1146
284	|	0.1389
286	|	0.1184
288	|	0.1615
290	|	0.1136
292	|	0.1407
294	|	0.1386
296	|	0.1155
298	|	0.1173
300	|	0.1217
----------------------

Array Size = 600000
Limit   |       Time
______________________
2	|	0.1914
4	|	0.1419
6	|	0.2539
8	|	0.128
10	|	0.1483
12	|	0.1323
14	|	0.1285
16	|	0.1265
18	|	0.1255
20	|	0.1826
22	|	0.1307
24	|	0.1355
26	|	0.1561
28	|	0.2055
30	|	0.1277
32	|	0.1291
34	|	0.125
36	|	0.1296
38	|	0.1981
40	|	0.1479
42	|	0.1441
44	|	0.137
46	|	0.1289
48	|	0.1599
50	|	0.1252
52	|	0.1326
54	|	0.1604
56	|	0.1259
58	|	0.1301
60	|	0.1307
62	|	0.1499
64	|	0.1659
66	|	0.1341
68	|	0.159
70	|	0.1485
72	|	0.1387
74	|	0.1359
76	|	0.181
78	|	0.1912
80	|	0.1339
82	|	0.1484
84	|	0.227
86	|	0.1303
88	|	0.1315
90	|	0.1578
92	|	0.1314
94	|	0.1327
96	|	0.1349
98	|	0.1323
100	|	0.1572
102	|	0.1338
104	|	0.1354
106	|	0.1353
108	|	0.1583
110	|	0.1511
112	|	0.1504
114	|	0.1637
116	|	0.1409
118	|	0.1621
120	|	0.1427
122	|	0.1395
124	|	0.1364
126	|	0.1406
128	|	0.1382
130	|	0.1405
132	|	0.1468
134	|	0.167
136	|	0.1416
138	|	0.1828
140	|	0.1499
142	|	0.1474
144	|	0.145
146	|	0.1642
148	|	0.1543
150	|	0.2162
152	|	0.1445
154	|	0.1723
156	|	0.1456
158	|	0.1464
160	|	0.149
162	|	0.1529
164	|	0.1553
166	|	0.1774
168	|	0.1542
170	|	0.1748
172	|	0.151
174	|	0.1552
176	|	0.155
178	|	0.153
180	|	0.1593
182	|	0.1522
184	|	0.1762
186	|	0.1583
188	|	0.1779
190	|	0.1837
192	|	0.1594
194	|	0.1604
196	|	0.1607
198	|	0.1605
200	|	0.157
202	|	0.1568
204	|	0.1806
206	|	0.1635
208	|	0.1884
210	|	0.1845
212	|	0.1651
214	|	0.2017
216	|	0.1689
218	|	0.1936
220	|	0.1668
222	|	0.1633
224	|	0.1826
226	|	0.1673
228	|	0.1865
230	|	0.1895
232	|	0.1915
234	|	0.1943
236	|	0.1879
238	|	0.1723
240	|	0.1678
242	|	0.2243
244	|	0.1662
246	|	0.1727
248	|	0.2553
250	|	0.1777
252	|	0.2233
254	|	0.1698
256	|	0.1984
258	|	0.1913
260	|	0.1848
262	|	0.1801
264	|	0.1753
266	|	0.174
268	|	0.2088
270	|	0.1755
272	|	0.1753
274	|	0.1754
276	|	0.2
278	|	0.2048
280	|	0.1807
282	|	0.2254
284	|	0.1835
286	|	0.1834
288	|	0.2062
290	|	0.183
292	|	0.184
294	|	0.2305
296	|	0.1869
298	|	0.1828
300	|	0.2047
----------------------

Array Size = 800000
Limit   |       Time
______________________
2	|	0.2104
4	|	0.2064
6	|	0.1997
8	|	0.2578
10	|	0.2035
12	|	0.1835
14	|	0.1776
16	|	0.2326
18	|	0.1825
20	|	0.2042
22	|	0.1808
24	|	0.1751
26	|	0.1988
28	|	0.1969
30	|	0.1868
32	|	0.1764
34	|	0.1747
36	|	0.201
38	|	0.1734
40	|	0.2141
42	|	0.1755
44	|	0.1762
46	|	0.1767
48	|	0.2007
50	|	0.2041
52	|	0.1784
54	|	0.1776
56	|	0.2165
58	|	0.1763
60	|	0.1991
62	|	0.1861
64	|	0.1824
66	|	0.1799
68	|	0.1835
70	|	0.1833
72	|	0.2276
74	|	0.2255
76	|	0.1853
78	|	0.1818
80	|	0.1823
82	|	0.1867
84	|	0.1838
86	|	0.1823
88	|	0.1845
90	|	0.1843
92	|	0.225
94	|	0.1867
96	|	0.1857
98	|	0.1887
100	|	0.2115
102	|	0.188
104	|	0.188
106	|	0.1949
108	|	0.1889
110	|	0.199
112	|	0.2154
114	|	0.2326
116	|	0.1887
118	|	0.2165
120	|	0.1943
122	|	0.2361
124	|	0.2139
126	|	0.2139
128	|	0.1939
130	|	0.1997
132	|	0.2218
134	|	0.2009
136	|	0.247
138	|	0.1958
140	|	0.2034
142	|	0.2363
144	|	0.217
146	|	0.2042
148	|	0.2049
150	|	0.2019
152	|	0.2363
154	|	0.2083
156	|	0.2027
158	|	0.2091
160	|	0.2268
162	|	0.2106
164	|	0.2131
166	|	0.2502
168	|	0.2357
170	|	0.2404
172	|	0.2062
174	|	0.2083
176	|	0.2346
178	|	0.2125
180	|	0.2194
182	|	0.2512
184	|	0.219
186	|	0.2401
188	|	0.2524
190	|	0.258
192	|	0.2189
194	|	0.2513
196	|	0.2175
198	|	0.2261
200	|	0.2299
202	|	0.2577
204	|	0.2571
206	|	0.2594
208	|	0.2446
210	|	0.3046
212	|	0.2184
214	|	0.2473
216	|	0.2212
218	|	0.2348
220	|	0.2756
222	|	0.2237
224	|	0.2235
226	|	0.2523
228	|	0.2671
230	|	0.2311
232	|	0.2262
234	|	0.2516
236	|	0.267
238	|	0.2358
240	|	0.2577
242	|	0.2564
244	|	0.2561
246	|	0.2331
248	|	0.2807
250	|	0.2561
252	|	0.2661
254	|	0.2408
256	|	0.2392
258	|	0.2693
260	|	0.2635
262	|	0.2841
264	|	0.2561
266	|	0.2502
268	|	0.2388
270	|	0.2752
272	|	0.2475
274	|	0.2504
276	|	0.2534
278	|	0.2748
280	|	0.2678
282	|	0.2812
284	|	0.2741
286	|	0.2713
288	|	0.2751
290	|	0.2519
292	|	0.2923
294	|	0.2829
296	|	0.2766
298	|	0.2547
300	|	0.2742
----------------------

Array Size = 900000
Limit   |       Time
______________________
2	|	0.2177
4	|	0.2738
6	|	0.2431
8	|	0.2115
10	|	0.2351
12	|	0.2088
14	|	0.2094
16	|	0.2648
18	|	0.211
20	|	0.2301
22	|	0.2046
24	|	0.2279
26	|	0.2274
28	|	0.2063
30	|	0.2063
32	|	0.2044
34	|	0.2048
36	|	0.2294
38	|	0.2047
40	|	0.2285
42	|	0.2465
44	|	0.2445
46	|	0.2057
48	|	0.2104
50	|	0.2234
52	|	0.2471
54	|	0.239
56	|	0.2152
58	|	0.2071
60	|	0.2085
62	|	0.2348
64	|	0.2083
66	|	0.2105
68	|	0.2103
70	|	0.249
72	|	0.2393
74	|	0.2143
76	|	0.2125
78	|	0.2133
80	|	0.2131
82	|	0.2132
84	|	0.2371
86	|	0.2372
88	|	0.2156
90	|	0.239
92	|	0.2165
94	|	0.2177
96	|	0.2179
98	|	0.2397
100	|	0.2188
102	|	0.2192
104	|	0.2678
106	|	0.2425
108	|	0.223
110	|	0.2219
112	|	0.2212
114	|	0.2442
116	|	0.223
118	|	0.2469
120	|	0.2271
122	|	0.2482
124	|	0.2255
126	|	0.2284
128	|	0.2276
130	|	0.2302
132	|	0.2278
134	|	0.2536
136	|	0.2562
138	|	0.2313
140	|	0.2302
142	|	0.2305
144	|	0.2605
146	|	0.2631
148	|	0.237
150	|	0.237
152	|	0.2398
154	|	0.236
156	|	0.2362
158	|	0.2368
160	|	0.2401
162	|	0.2379
164	|	0.2427
166	|	0.2877
168	|	0.2579
170	|	0.249
172	|	0.2663
174	|	0.2412
176	|	0.2442
178	|	0.248
180	|	0.2951
182	|	0.244
184	|	0.2467
186	|	0.2698
188	|	0.2709
190	|	0.249
192	|	0.272
194	|	0.2514
196	|	0.2501
198	|	0.2499
200	|	0.2775
202	|	0.2806
204	|	0.2543
206	|	0.2613
208	|	0.316
210	|	0.2835
212	|	0.2562
214	|	0.2778
216	|	0.2634
218	|	0.2751
220	|	0.2628
222	|	0.268
224	|	0.2662
226	|	0.2846
228	|	0.265
230	|	0.2711
232	|	0.3008
234	|	0.2708
236	|	0.266
238	|	0.2664
240	|	0.2684
242	|	0.27
244	|	0.2762
246	|	0.2765
248	|	0.3197
250	|	0.2771
252	|	0.2716
254	|	0.331
256	|	0.3046
258	|	0.275
260	|	0.2751
262	|	0.3388
264	|	0.2768
266	|	0.276
268	|	0.3043
270	|	0.2833
272	|	0.2784
274	|	0.2797
276	|	0.2816
278	|	0.2817
280	|	0.3279
282	|	0.2867
284	|	0.2828
286	|	0.3401
288	|	0.2925
290	|	0.2931
292	|	0.333
294	|	0.3087
296	|	0.317
298	|	0.2894
300	|	0.2922
----------------------

Array Size = 1000000
Limit   |       Time
______________________
2	|	0.2461
4	|	0.2445
6	|	0.297
8	|	0.2382
10	|	0.2357
12	|	0.2372
14	|	0.2325
16	|	0.2329
18	|	0.231
20	|	0.2541
22	|	0.2302
24	|	0.2298
26	|	0.2532
28	|	0.2296
30	|	0.2306
32	|	0.2287
34	|	0.2286
36	|	0.2514
38	|	0.2302
40	|	0.2522
42	|	0.2529
44	|	0.2302
46	|	0.2307
48	|	0.2687
50	|	0.2313
52	|	0.2428
54	|	0.2683
56	|	0.2517
58	|	0.2634
60	|	0.2649
62	|	0.2686
64	|	0.2669
66	|	0.2593
68	|	0.2573
70	|	0.2604
72	|	0.2758
74	|	0.2592
76	|	0.2629
78	|	0.2521
80	|	0.2485
82	|	0.2617
84	|	0.2412
86	|	0.2402
88	|	0.2638
90	|	0.2976
92	|	0.2449
94	|	0.2688
96	|	0.2821
98	|	0.2435
100	|	0.2465
102	|	0.2466
104	|	0.245
106	|	0.2478
108	|	0.2471
110	|	0.2486
112	|	0.2473
114	|	0.2801
116	|	0.2532
118	|	0.2505
120	|	0.291
122	|	0.2547
124	|	0.276
126	|	0.2543
128	|	0.2763
130	|	0.2801
132	|	0.2853
134	|	0.2598
136	|	0.2573
138	|	0.2616
140	|	0.2809
142	|	0.2603
144	|	0.3013
146	|	0.2611
148	|	0.2629
150	|	0.2905
152	|	0.3017
154	|	0.2647
156	|	0.2856
158	|	0.2686
160	|	0.3031
162	|	0.2704
164	|	0.2943
166	|	0.3155
168	|	0.269
170	|	0.2717
172	|	0.2771
174	|	0.276
176	|	0.277
178	|	0.2922
180	|	0.3459
182	|	0.321
184	|	0.2768
186	|	0.3047
188	|	0.3251
190	|	0.28
192	|	0.2798
194	|	0.2832
196	|	0.3193
198	|	0.2934
200	|	0.3339
202	|	0.2884
204	|	0.314
206	|	0.2874
208	|	0.2894
210	|	0.3208
212	|	0.2903
214	|	0.3181
216	|	0.3389
218	|	0.3325
220	|	0.3315
222	|	0.2907
224	|	0.2936
226	|	0.3036
228	|	0.3009
230	|	0.3387
232	|	0.2985
234	|	0.3008
236	|	0.3225
238	|	0.3032
240	|	0.3421
242	|	0.3433
244	|	0.329
246	|	0.3232
248	|	0.3361
250	|	0.3322
252	|	0.3282
254	|	0.3541
256	|	0.3066
258	|	0.306
260	|	0.308
262	|	0.3624
264	|	0.3147
266	|	0.3334
268	|	0.3124
270	|	0.325
272	|	0.3181
274	|	0.3146
276	|	0.381
278	|	0.32
280	|	0.3234
282	|	0.3488
284	|	0.3181
286	|	0.3229
288	|	0.3575
290	|	0.3252
292	|	0.3451
294	|	0.3514
296	|	0.3315
298	|	0.349
300	|	0.325
----------------------

Array Size = 1100000
Limit   |       Time
______________________
2	|	0.2737
4	|	0.2716
6	|	0.2697
8	|	0.2872
10	|	0.2646
12	|	0.263
14	|	0.26
16	|	0.2823
18	|	0.258
20	|	0.2578
22	|	0.2905
24	|	0.3201
26	|	0.2793
28	|	0.258
30	|	0.2793
32	|	0.2565
34	|	0.2779
36	|	0.2871
38	|	0.2798
40	|	0.278
42	|	0.2992
44	|	0.2569
46	|	0.26
48	|	0.2843
50	|	0.2592
52	|	0.281
54	|	0.2601
56	|	0.2597
58	|	0.2593
60	|	0.2848
62	|	0.261
64	|	0.2648
66	|	0.2612
68	|	0.2633
70	|	0.2631
72	|	0.2661
74	|	0.2874
76	|	0.2645
78	|	0.2894
80	|	0.2664
82	|	0.2683
84	|	0.3709
86	|	0.3163
88	|	0.2702
90	|	0.2694
92	|	0.3104
94	|	0.2797
96	|	0.3102
98	|	0.294
100	|	0.2743
102	|	0.296
104	|	0.3316
106	|	0.2747
108	|	0.2783
110	|	0.2774
112	|	0.3103
114	|	0.3442
116	|	0.3223
118	|	0.448
120	|	0.3348
122	|	0.3413
124	|	0.2987
126	|	0.3184
128	|	0.3425
130	|	0.3232
132	|	0.8566
134	|	0.6258
136	|	0.3526
138	|	0.4009
140	|	0.3344
142	|	0.3258
144	|	0.3032
146	|	0.2943
148	|	0.2938
150	|	0.2933
152	|	0.2932
154	|	0.2968
156	|	0.2959
158	|	0.3176
160	|	0.2995
162	|	0.2982
164	|	0.3
166	|	0.3015
168	|	0.3261
170	|	0.3089
172	|	0.3131
174	|	0.3037
176	|	0.3053
178	|	0.3131
180	|	0.3274
182	|	0.3686
184	|	0.3316
186	|	0.3281
188	|	0.3393
190	|	0.3323
192	|	0.3214
194	|	0.3195
196	|	0.3411
198	|	0.3461
200	|	0.3648
202	|	0.3454
204	|	0.3419
206	|	0.3504
208	|	0.3571
210	|	0.3486
212	|	0.3517
214	|	0.3685
216	|	0.3955
218	|	0.3936
220	|	0.3952
222	|	0.3974
224	|	0.4131
226	|	0.4377
228	|	0.431
230	|	0.4245
232	|	0.4316
234	|	0.4174
236	|	0.4414
238	|	0.4221
240	|	0.3708
242	|	0.3832
244	|	0.3789
246	|	0.3768
248	|	0.3989
250	|	0.4005
252	|	0.3842
254	|	0.3895
256	|	0.3735
258	|	0.4041
260	|	0.3885
262	|	0.3844
264	|	0.4062
266	|	0.3859
268	|	0.3723
270	|	0.3933
272	|	0.3986
274	|	0.3887
276	|	0.4186
278	|	0.389
280	|	0.3979
282	|	0.3939
284	|	0.3723
286	|	0.4045
288	|	0.44
290	|	0.4337
292	|	0.4061
294	|	0.4232
296	|	0.3975
298	|	0.4008
300	|	0.4322
---------------------- 

DISCUSSION
----------
- Based off of the times from the table, it seems that any recursion limit that 
was a part of an array size that was less than 40,000 was minimal. The times 
often ranged less than 0.02 seconds. 
*/