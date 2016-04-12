var BMPsparamObj = [
	{
		fid: 1, 
		id: "a",
		name: "等高草灌带",
		en: "dgcgd",
		instruction: "在强度水土流失山地，沿等高线挖小水平沟，水平沟的断面挖成梯形，水平沟按品字形排列，沟间距为200cm，开挖土用于外侧作梗。植苗播种前好施肥，均匀撒施于沟底。在沟内种植灌木、乔木，乔木以乡土树种为主，灌木以胡枝子为主，沟埂及沟内撒播草籽，以宽叶雀稗种子或百喜草种子为主。",
		parameter: [
			{number: 1, param: "Interc_max", info: "Maximum Interception Capacity", process: "冠层截留", unit: "mm", type: "+", value: "2"},
			{number: 2, param: "Depression", info: "填洼量", process: "填洼", unit: "mm", type: "*", value: "3"},
			{number: 3, param: "Conductivity", info: "Soil hydraulic conductivity", process: "入渗", unit: "mm/hr", type: "*", value: "8"},
			{number: 4, param: "MANNING", info: "Manning", process: "坡面汇流", unit: " ", type: "+", value: "0.3"},
			{number: 5, param: "USLE_C", info: "The cover management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.0125"},
			{number: 6, param: "USLE_P", info: "The P management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.1"},
		],
		result: "（1）促进沟内的乔、灌、草快速覆盖地表，增大冠层截流量；<br>（2）可以拦蓄较多的地表径流；<br>（3）降低径流速率。"
	},
	{
		fid: 2, 
		id: "b",
		name: "低效林改造",
		en: "dxlgz",
		instruction: "在立地条件较差的中、轻度水土流失山地，马尾松（或其他乔木、灌木）林分密度在120株/亩以上的林地，每亩选择100株幼树，在其树冠投影上段挖40*30*30cm施肥穴，每穴施有机复混肥0.25kg，后覆土踩实。通过抚育施肥改造，促进老头松生长，促长其他伴生树草，达到植被恢复之目的。",
		parameter: [
		    {number: 1, param: "Depression", info: "填洼量", process: "填洼", unit: "mm", type: "*", value: "1.5"},
		    {number: 2, param: "Interc_max", info: "Maximum Interception Capacity", process: "冠层截留", unit: "mm", type: "+", value: "2"},
		    {number: 3, param: "Conductivity", info: "Soil hydraulic conductivity", process: "入渗", unit: "mm/hr", type: "*", value: "4.2"},
		    {number: 4, param: "MANNING", info: "Manning", process: "坡面汇流", unit: " ", type: "+", value: "0.05"},
		    {number: 5, param: "USLE_C", info: "The cover management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.024"},
		    {number: 6, param: "USLE_P", info: "The P management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.2"},
		],
		result: "（1）促进植物的生长，增加植被覆盖度，增大冠层截流量；<br>（2）增加地表粗糙度，降低坡面汇流速度。"
	},
	{
		fid: 3, 
		id: "c",
		name: "草灌乔混交",
		en: "cgqhj",
		instruction: "根据植物地带性原理以及侵蚀坡地立地条件、森林群落重要值和植物对养分利用效率等选种的原则，筛选出草本（包括百喜草、宽叶雀稗、类芦和芒萁等）、灌木（胡枝子）、乔木（包括马尾松、木荷、枫香、杨梅等）的先锋植物品种，实施草、灌、乔混交治理模式。",
		parameter: [
			{number: 1, param: "Depression", info: "填洼量", process: "填洼", unit: "mm", type: "*", value: "2"},
			{number: 2, param: "Interc_max", info: "Maximum Interception Capacity", process: "冠层截留", unit: "mm", type: "+", value: "2.5"},
			{number: 3, param: "Conductivity", info: "Soil hydraulic conductivity", process: "入渗", unit: "mm/hr", type: "*", value: "10"},
			{number: 4, param: "MANNING", info: "Manning", process: "坡面汇流", unit: " ", type: "+", value: "0.1"},
			{number: 5, param: "USLE_C", info: "The cover management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.015"},
			{number: 6, param: "USLE_P", info: "The P management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.2"},
		],
		result: "（1）促进植物的生长，增加植被覆盖度，增大冠层截流量；<br>（2）增加地表粗糙度，降低坡面汇流速度。"
	},
	{
		fid: 4, 
		id: "d",
		name: "小穴播草",
		en: "xxbc",
		instruction: "在强度侵蚀坡地上重建植被，需要注重近地表林下植被的建设。根据强度侵蚀坡地立地条件，提出了小穴播草治理模式。该模式符合自然植被的演替规律，以草灌先行，种草灌促林，能在较短时间内控制水土流失，是陡坡地重建植被的有效途径，可用于植被恢复差，侵蚀地立地条件差，集中于山脊、山坡上部的地区。",
		parameter: [
		    {number: 1, param: "Depression", info: "填洼量", process: "填洼", unit: "mm", type: "*", value: "1.5"},
		    {number: 2, param: "Interc_max", info: "Maximum Interception Capacity", process: "冠层截留", unit: "mm", type: "+", value: "1"},
		    {number: 3, param: "Conductivity", info: "Soil hydraulic conductivity", process: "入渗", unit: "mm/hr", type: "*", value: "4"},
		    {number: 4, param: "MANNING", info: "Manning", process: "坡面汇流", unit: " ", type: "+", value: "0.15"},
		    {number: 5, param: "USLE_C", info: "The cover management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.019"},
		    {number: 6, param: "USLE_P", info: "The P management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.2"},
		],
		result: "（1）增大地表蓄水能力，可以拦蓄较多的地表径流；<br>（2）增加地表覆盖度，降低径流速率。"
	},
	{
		fid: 5, 
		id: "e",
		name: "茶果园坡改梯",
		en: "chgypgt",
		instruction: "对顺坡种植及坡田平台不达标的茶果园进行改造，做到前有埂、后有沟，并在田埂种草覆盖，田面套种豆科植物，使径流被沟、埂、草层层拦截、降速，达到泥沙不下山，雨水不冲埂的效果。",
		parameter: [
			{number: 1, param: "Interc_max", info: "Maximum Interception Capacity", process: "冠层截留", unit: "mm", type: "+", value: "1"},
			{number: 2, param: "Slope", info: "Slope", process: "坡面汇流、坡面侵蚀", unit: "%", type: "=", value: "0.001"},
			{number: 3, param: "Depression", info: "填洼量", process: "填洼", unit: "mm", type: "*", value: "5"},
			{number: 4, param: "Conductivity", info: "Soil hydraulic conductivity", process: "入渗", unit: "mm/hr", type: "*", value: "3"},
			{number: 5, param: "MANNING", info: "Manning", process: "坡面汇流", unit: " ", type: "+", value: "0.05"},
			{number: 6, param: "USLE_C", info: "The cover management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.008"},
			{number: 7, param: "USLE_P", info: "The P management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.03"},
		],
		result: "（1）增大小洼地的蓄水能力，减少地表径流；<br>（2）减少山坡的坡度，降低径流速度；<br>（3）增加泥沙的沉淀，减少径流的侵蚀力，防治形成细沟和沟壑。"
	},
	{
		fid: 6, 
		id: "f",
		name: "封禁",
		en: "fj",
		instruction: "根据现存不同类型植被的恢复演替阶段，在不破坏天然植被分布格局和生存环境的条件下，充分依靠生态环境自身的力量恢复植被，严禁人为干扰，如禁止开垦、采薪、放牧等。大范围封禁治理要与局部地区补植措施结合，补植树种以阔叶林为主，促进形成针阔混交林。",
		parameter: [
			{number: 1, param: "Interc_max", info: "Maximum Interception Capacity", process: "冠层截留", unit: "mm", type: "+", value: "1"},
			{number: 2, param: "Conductivity", info: "Soil hydraulic conductivity", process: "入渗", unit: "mm/hr", type: "*", value: "10"},
			{number: 3, param: "MANNING", info: "Manning", process: "坡面汇流", unit: " ", type: "+", value: "0.1"},
			{number: 4, param: "USLE_C", info: "The cover management factor", process: "坡面侵蚀", unit: " ", type: "=", value: "0.008"},
		],
		result: "（1）促进植物的生长，增加植被覆盖度，增大冠层截流量；<br>（2）增加地表粗糙度，降低坡面汇流速度。"
	}
]