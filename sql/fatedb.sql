/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : fatedb

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2019-07-29 09:35:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `dice`
-- ----------------------------
DROP TABLE IF EXISTS `dice`;
CREATE TABLE `dice` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT 'mf',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dice
-- ----------------------------
INSERT INTO `dice` VALUES ('1', '帕图纳克斯', '帕帕', 'PT', 'jj');

-- ----------------------------
-- Table structure for `fatemsg`
-- ----------------------------
DROP TABLE IF EXISTS `fatemsg`;
CREATE TABLE `fatemsg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `hp` varchar(255) DEFAULT NULL,
  `sp` varchar(255) DEFAULT NULL,
  `mp` int(11) DEFAULT '0',
  `maxmp` int(11) DEFAULT '0',
  `type` int(11) DEFAULT '0',
  `fromUser` varchar(255) DEFAULT NULL,
  `userID` varchar(255) DEFAULT NULL,
  `usergroup` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_name` (`name`),
  KEY `fk_fromUser` (`fromUser`),
  KEY `fk_userID` (`userID`),
  KEY `fk_usergroup` (`usergroup`)
) ENGINE=InnoDB AUTO_INCREMENT=210 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fatemsg
-- ----------------------------
INSERT INTO `fatemsg` VALUES ('207', '艾瑞尔', '□1□2□3', '□1□2', '7', '7', '0', null, '491823948', '781968580');
INSERT INTO `fatemsg` VALUES ('208', '卡尔萨斯 科穆宁', '□1□2□3', '□1□2', '10', '10', '0', null, '312984729', '781968580');
INSERT INTO `fatemsg` VALUES ('209', '阿什利·尤里乌斯', '□1□2□3', '□1□2', '10', '10', '0', null, '1169063282', '781968580');

-- ----------------------------
-- Table structure for `jjdata`
-- ----------------------------
DROP TABLE IF EXISTS `jjdata`;
CREATE TABLE `jjdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `msg` text,
  `dData` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jjdata
-- ----------------------------
INSERT INTO `jjdata` VALUES ('101', '背景', '设定', '    在人类第一次飞往太空的一百五十年后，地球的资源已经接近枯竭。为了更好的开拓宇宙的资源，人类组建了地球联邦，随着人类在外星殖民范围的扩大，后又改为更为集权的人类联邦。\r\n    人类联邦执行着严格的地球至上主义，对其他的殖民地进行严苛的技术封锁，所有的科学家都只能在地球工作。除此之外还对殖民地的文化也进行着的控制。这些举措引起了许多殖民地人民的不满。\r\n    终于，在公元2431年，三个边境的殖民地宣布脱离人类联邦，随后许多殖民地夜云起响应，最后达到了十三个之多。也被称为十三殖民地独立战争。\r\n    在战争初期，人类联邦占有主动权，但随着时间的推移，由于缺乏殖民地的输血，人类联邦的军队开始逐渐疲于奔命。最终，十三殖民地的技术人员成功的启动了月球发动机，将月球推向地球，将地球的生态圈彻底毁灭，赢得了战争。殖民地的人民将这一年称之为‘新纪元元年’\r\n    但地球毁灭之后，由于长时间的技术封锁，导致殖民地们并没有继续发展技术的能力，甚至科技水平相比人类联邦时期还有了显著的倒退。各个殖民地之间的矛盾也在之后彻底爆发，人类联邦的原本领地碎成了十几个人类政权。失去了秩序的约束，星际海盗也开始崛起。\r\n    就这样，时间度过了快六百年。', null);
INSERT INTO `jjdata` VALUES ('201', '装甲压力', '额外规则', '装甲压力指代机甲使用装甲抵抗攻击的能力。装甲在抵抗实弹时较护盾有优势。在使用装甲抵抗实弹攻击时会降降低每次攻击的实弹防御的伤害。\r\n装甲压力可以由结构技能的提升而获得提升，初始为2□3□', null);
INSERT INTO `jjdata` VALUES ('202', '能量压力', '额外规则', '能量压力指代机甲消耗能量的水平。在使用能量系武器或者一些特殊的装备时需消耗能量。\r\n能量也可以用于开启护盾，每点能量可以抵御1的伤害，如果使用能量攻击攻击护盾，且所有伤害都被护盾承担，那么受到的伤害会降低能量防御的数值。\r\n护盾在两回合内不能承受超过能量压力上限一半的伤害，否则会造成护盾过载，直到两个回合后才能再次使用护盾。', null);
INSERT INTO `jjdata` VALUES ('1801', '海盗杂兵', '人物', '因为各种理由加入海盗的普通人，海盗的底层战力。\r\n虽然能开上机甲的已经称不上是杂兵的级别，但杂鱼终究还是杂鱼。', '窃术:2|打斗:1|欺骗:1|机师等级:1|');
INSERT INTO `jjdata` VALUES ('2601', 'E-44“铁剑”', '机甲', '莱恩军工为莱恩-索斯星域共和国开发的陆宙二用机甲。\r\n为了配合指挥，机体较为注重单体机动与索敌能力，相对出力与结构强度较弱。\r\n在故事开始时已经属于过时的机甲，正逐步被共和国的E-54型机甲替换退役。', '制造商:莱恩军工|类型:原型|机体名字:EMI-E44|装配值:3|能量:1|结构:1|机动:2|感应:2|装配值:3|特殊装备:附加装甲包|特技1:附加装甲包|最大装甲压力:2□3□3□|最大能量压力:8|');
INSERT INTO `jjdata` VALUES ('2802', 'Pi-S2“海盗拼装机”', '机甲', '海盗所使用的拼装机甲。\r\n对比完全是民用机改装的S1来说，S2使用的部件更好一些。\r\n但仍然改变不了这是台杂鱼机的事实。', '制造商:海盗|类型:海盗型|机体名字:Pi-S2|装配值:3|能量:1|结构:2|机动:1|感应:1|装配值:3|特殊装备1:加压喷射器|特技1:掠袭|特技2:杂兵|最大装甲压力:2□3□3□|最大能量压力:8|');
INSERT INTO `jjdata` VALUES ('2803', 'Pi-A6\"海盗队长机\"', '机甲', '队长用的海盗机甲。作为海盗中的高级领导，队长机甲一般都是使用海盗们所能缴获的最好的机甲改造而成。\r\n与海盗的拼装机一样，队长机也是各有特色。但无论如何，海盗的机体都有一种共同点——保养得很烂。', '制造商:海盗|类型:海盗型|机体名字:Pi-A6|装配值:5|能量:3|结构:5|机动:4|感应:2|装配值:5|特殊装备1:高强度抓钩|特技1:掠袭|特技2:高强度抓钩|最大装甲压力:2□3□3□4□4□|最大能量压力:13|');
INSERT INTO `jjdata` VALUES ('3601', 'E-A2磁轨步枪', '武器', '莱恩军工制作的磁轨步枪。\r\n相较于标准的磁轨步枪，E-A2换装了更稳定的弹药，并加大了弹夹容量。\r\n但由于插槽适配问题，可携带的弹夹比旧式磁轨步枪少一个弹夹，不得不说是一个遗憾。', '名称:E-A2磁轨步枪|伤害类型:动能|武器类型:中型枪械|插槽:3|伤害:6|伤害段数:1|命中:2|弹夹载弹:7|消耗:1|弹夹数:2|最高攻击次数:1|最小射程:0|最大射程:2|占用:1|');
INSERT INTO `jjdata` VALUES ('3602', 'E-S2军用光束剑', '武器', '莱恩军工开发的军用光束剑。\r\n与通用的光束剑并没有什么性能差别，但插槽修改为了共和国机甲所专用的插槽。', '名称:E-S2军用光束剑|伤害类型:能量|武器类型:中型近战|插槽:2|伤害:7|伤害段数:1|命中:1|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|');
INSERT INTO `jjdata` VALUES ('3603', 'E-AlC连射步枪', '武器', '莱恩军工开发的试验型连射步枪。\r\n装备了莱恩军工开发的新式高速弹，威力与命中都更上一层楼。\r\n但与之相对，此步枪的弹夹只够一次连射，弹夹数量也较少，大大限制了这款步枪的发挥。', '名称:E-AlC连射步枪|伤害类型:动能|武器类型:中型枪械|插槽:2|伤害:6|伤害段数:1|命中:1|弹夹载弹:3|消耗:1|弹夹数:4|最高攻击次数:3|最小射程:0|最大射程:2|占用:2|');
INSERT INTO `jjdata` VALUES ('3801', '海盗短枪', '武器', '海盗使用的磁轨短枪，虽然经常会有电子设备老化，保养不佳导致的磁圈破损等问题。\r\n嘿，兄弟，它还能开火呢，这还不够吗？', '名称:海盗短枪|伤害类型:动能|武器类型:轻型枪械|插槽:1|伤害:5|伤害段数:1|命中:0|弹夹载弹:10|消耗:1|弹夹数:2|最高攻击次数:1|最小射程:0|最大射程:1|占用:1|');
INSERT INTO `jjdata` VALUES ('3802', '海盗热匕', '武器', '海盗们很喜欢的短型热能剑，它通常来自于海盗们把正常长度的热能剑削短。\r\n真不知道这种习俗是从哪儿来的。', '名称:海盗热匕|伤害类型:动能|武器类型:轻型近战|插槽:1|伤害:4|伤害段数:1|命中:2|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|');
INSERT INTO `jjdata` VALUES ('3803', '海盗磁轨步枪', '武器', '海盗们使用的磁轨步枪，加入了很多不知所以的配件，\r\n但威力是真的不错。', '名称:海盗磁轨步枪|伤害类型:动能|武器类型:中型枪械|插槽:3|伤害:9|伤害段数:1|命中:0|弹夹载弹:4|消耗:1|弹夹数:3|最高攻击次数:1|最小射程:0|最大射程:2|占用:1|');
INSERT INTO `jjdata` VALUES ('3804', '海盗斩刃长枪', '武器', null, '名称:海盗斩刃长枪|伤害类型:动能|武器类型:重型近战|插槽:3|伤害:10|伤害段数:1|命中:1|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:2|');
INSERT INTO `jjdata` VALUES ('80001', '未知1', '人物', null, '感知:5|工程:5|电子:4|打斗:3|射击:3|学识:2|驾驶:2|工艺:2|调查:2|机师等级:3|-经济压力:3|');

-- ----------------------------
-- Table structure for `jjuser`
-- ----------------------------
DROP TABLE IF EXISTS `jjuser`;
CREATE TABLE `jjuser` (
  `key` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userID` varchar(20) DEFAULT NULL,
  `userName` varchar(40) DEFAULT NULL,
  `userOldName` varchar(40) DEFAULT NULL,
  `usergroup` varchar(20) DEFAULT NULL,
  `userAttribute` varchar(255) DEFAULT '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|',
  `userData` text,
  `userItem` text,
  `userEquip` varchar(255) DEFAULT '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|',
  PRIMARY KEY (`key`),
  KEY `userName` (`userName`),
  KEY `userID` (`userID`),
  KEY `usergroup` (`usergroup`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jjuser
-- ----------------------------
INSERT INTO `jjuser` VALUES ('00000002', '747069596', '夙玖怜( lugroma)', '夙玖怜( lugroma)', '790664778', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `jjuser` VALUES ('00000003', '1738212350', '苍牙', '一念三千', '790664778', '感应:0|亲善:2|意志:2|挑拨:2|运动:0|窃术:0|人脉:1|工艺:0|欺骗:0|射击:3|移情:1|打斗:3|调查:0|学识:0|资源:1|体魄:0|工程:1|电子:0|驾驶:0|机师等级:1|感知:4|', '命运点:3|经济压力:4|生理压力:□1□2|心理压力:□1□2□3|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `jjuser` VALUES ('00000010', '1147580553', 'Frank', 'Frank', '790664778', '感应:0|亲善:0|意志:0|挑拨:1|运动:3|窃术:4|人脉:2|工艺:0|欺骗:2|射击:1|移情:0|打斗:2|调查:0|学识:0|资源:1|体魄:1|工程:0|电子:0|驾驶:0|机师等级:1|感知:3|驾驶等级:1|', '命运点:3|经济压力:4|生理压力:□1□2□3|心理压力:□1□2|', '', '');
INSERT INTO `jjuser` VALUES ('00000012', '553859318', 'GM', '盘尼西林不会过敏', '790664778', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', '', '', '');
INSERT INTO `jjuser` VALUES ('00000015', '553859318', 'BBGM', '盘尼西林不会过敏', '650903307', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', '', '', '');
INSERT INTO `jjuser` VALUES ('00000033', '445630541', '风林戈', '风林戈', '650903307', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');

-- ----------------------------
-- Table structure for `mecha`
-- ----------------------------
DROP TABLE IF EXISTS `mecha`;
CREATE TABLE `mecha` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) DEFAULT NULL,
  `uid` varchar(255) DEFAULT NULL,
  `mEN` int(11) DEFAULT NULL,
  `mST` int(11) DEFAULT NULL,
  `mMO` int(11) DEFAULT NULL,
  `mSE` int(11) DEFAULT NULL,
  `attData` varchar(255) DEFAULT NULL,
  `weapon1Data` varchar(255) DEFAULT NULL,
  `weapon2Data` varchar(255) DEFAULT NULL,
  `weapon3Data` varchar(255) DEFAULT NULL,
  `weapon4Data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mecha
-- ----------------------------
INSERT INTO `mecha` VALUES ('5', 'CMUF-1033P', 'qq:553859318|group:790664778|', '3', '2', '2', '3', '制造商:人类联邦宇宙军|类型:原型|装配值:4|特殊装备1:加压喷射器|特殊装备2:加压喷射器|特殊装备3:0|特技1:联邦护盾序列|特技2:加压喷射|特技3:附加能量池|特技4:0|最大装甲压力:2□3□3□|最大能量压力:13|', '名称:MR1033步枪|伤害类型:动能|武器类型:中型枪械|插槽:3|伤害:9|伤害段数:1|命中:2|弹夹载弹:5|消耗:1|弹夹数:3|最高攻击次数:1|最小射程:0|最大射程:3|占用:1|', '名称:BS1033光束剑|伤害类型:能量|武器类型:中型近战|插槽:2|伤害:10|伤害段数:1|命中:2|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|', null, null);
INSERT INTO `mecha` VALUES ('7', 'EMI-E44-E', 'group:790664778|', '2', '2', '2', '3', '制造商:莱恩军工|类型:均衡型|装配值:3|特殊装备1:附加装甲包|特殊装备2:0|特殊装备3:0|特技1:平衡控制|特技2:附加装甲包|特技3:0|特技4:0|最大装甲压力:2□3□3□|最大能量压力:10|', '名称:E-AlC连射步枪|伤害类型:动能|武器类型:中型枪械|插槽:2|伤害:6|伤害段数:1|命中:1|弹夹载弹:3|消耗:1|弹夹数:6|最高攻击次数:3|最小射程:0|最大射程:2|占用:1|', '名称:E-S2军用光束剑|伤害类型:能量|武器类型:中型近战|插槽:2|伤害:7|伤害段数:1|命中:1|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|', '名称:E-AS磁轨霰弹枪|伤害类型:动能|武器类型:突进枪械|插槽:2|伤害:4|伤害段数:3|命中:0|弹夹载弹:3|消耗:1|弹夹数:3|最高攻击次数:1|最小射程:0|最大射程:1|占用:1|', null);
INSERT INTO `mecha` VALUES ('8', 'EMI-E44-E', '0', '2', '2', '2', '3', '制造商:莱恩军工|类型:均衡型|装配值:3|特殊装备1:附加装甲包|特殊装备2:0|特殊装备3:0|特技1:平衡控制|特技2:附加装甲包|特技3:0|特技4:0|最大装甲压力:2□3□3□|最大能量压力:10|', '名称:E-AlC连射步枪|伤害类型:动能|武器类型:中型枪械|插槽:2|伤害:6|伤害段数:1|命中:1|弹夹载弹:3|消耗:1|弹夹数:6|最高攻击次数:3|最小射程:0|最大射程:2|占用:1|', '名称:E-S2军用光束剑|伤害类型:能量|武器类型:中型近战|插槽:2|伤害:7|伤害段数:1|命中:1|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|', '名称:E-AS磁轨霰弹枪|伤害类型:动能|武器类型:突进枪械|插槽:2|伤害:4|伤害段数:3|命中:0|弹夹载弹:3|消耗:1|弹夹数:3|最高攻击次数:1|最小射程:0|最大射程:1|占用:1|', null);
INSERT INTO `mecha` VALUES ('9', 'EMI-E44-E', null, '2', '2', '2', '3', '制造商:莱恩军工|类型:均衡型|装配值:3|特殊装备1:附加装甲包|特殊装备2:0|特殊装备3:0|特技1:平衡控制|特技2:附加装甲包|特技3:0|特技4:0|最大装甲压力:2□3□3□|最大能量压力:10|', '名称:E-AlC连射步枪|伤害类型:动能|武器类型:中型枪械|插槽:2|伤害:6|伤害段数:1|命中:1|弹夹载弹:3|消耗:1|弹夹数:6|最高攻击次数:3|最小射程:0|最大射程:2|占用:1|', '名称:E-S2军用光束剑|伤害类型:能量|武器类型:中型近战|插槽:2|伤害:7|伤害段数:1|命中:1|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|', '名称:E-AS磁轨霰弹枪|伤害类型:动能|武器类型:突进枪械|插槽:2|伤害:4|伤害段数:3|命中:0|弹夹载弹:3|消耗:1|弹夹数:3|最高攻击次数:1|最小射程:0|最大射程:1|占用:1|', null);
INSERT INTO `mecha` VALUES ('10', 'E-44“铁剑”', 'qq:1738212350|group:790664778|', '1', '1', '2', '2', '制造商:莱恩军工|类型:原型|装配值:3|特殊装备1:加压喷射器|特殊装备2:0|特殊装备3:0|特技1:附加装甲包|特技2:0|特技3:0|特技4:0|最大装甲压力:2□3□3□|最大能量压力:8|', '名称:E-A2磁轨步枪|伤害类型:动能|武器类型:中型枪械|插槽:3|伤害:6|伤害段数:1|命中:2|弹夹载弹:7|消耗:1|弹夹数:2|最高攻击次数:1|最小射程:0|最大射程:2|占用:1|', '名称:E-S2军用光束剑|伤害类型:能量|武器类型:中型近战|插槽:2|伤害:7|伤害段数:1|命中:1|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|', null, null);
INSERT INTO `mecha` VALUES ('11', 'CMUF-1033P', 'qq:553859318|group:650903307|', '3', '2', '2', '3', '制造商:人类联邦宇宙军|类型:原型|装配值:4|特殊装备1:加压喷射器|特殊装备2:加压喷射器|特殊装备3:0|特技1:联邦护盾序列|特技2:加压喷射|特技3:附加能量池|特技4:0|最大装甲压力:2□3□3□|最大能量压力:13|', '名称:MR1033步枪|伤害类型:动能|武器类型:中型枪械|插槽:3|伤害:9|伤害段数:1|命中:2|弹夹载弹:5|消耗:1|弹夹数:3|最高攻击次数:1|最小射程:0|最大射程:3|占用:1|', '名称:BS1033光束剑|伤害类型:能量|武器类型:中型近战|插槽:2|伤害:10|伤害段数:1|命中:2|弹夹载弹:\\|消耗:\\|弹夹数:\\|最高攻击次数:1|最小射程:0|最大射程:0|占用:1|', null, null);

-- ----------------------------
-- Table structure for `mechalib`
-- ----------------------------
DROP TABLE IF EXISTS `mechalib`;
CREATE TABLE `mechalib` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) DEFAULT NULL,
  `mEN` int(11) DEFAULT NULL,
  `mST` int(11) DEFAULT NULL,
  `mMO` int(11) DEFAULT NULL,
  `mSE` int(11) DEFAULT NULL,
  `attData` varchar(255) DEFAULT NULL,
  `weapon1Data` varchar(255) DEFAULT NULL,
  `weapon2Data` varchar(255) DEFAULT NULL,
  `weapon3Data` varchar(255) DEFAULT NULL,
  `weapon4Data` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mechalib
-- ----------------------------

-- ----------------------------
-- Table structure for `mfdata`
-- ----------------------------
DROP TABLE IF EXISTS `mfdata`;
CREATE TABLE `mfdata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `msg` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2302 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mfdata
-- ----------------------------
INSERT INTO `mfdata` VALUES ('301', '炼金', '额外技能', '第二世代的炼金术多是对第一世代魔导工业的还原，由于大灾难对魔法知识流传的灾难性破坏，上世代大部分炼金术已经完全遗失，只有少部分天资聪慧之人才得窥炼金之道。\r\n炼金术主要能制作一些魔法道具来全方位帮助冒险，高等级的炼金术士甚至能制作并操作自律炼金兵器来形成以一敌多的境地。\r\n在没有特殊修正的情况下，炼金无法达到五及以上\r\n等级 描述：\r\n1.可以识别一些简单炼金道具并得知破解的方法。\r\n2.可以制造一些简单的炼金道具。\r\n3.制造稍微复杂一些的炼金道具，操作单个自律炼金兵器。\r\n4.制造更为复杂的炼金道具，制作并操作两个自律炼金兵器。\r\n预设特技:\r\n轻量产品：炼金道具的重量变为一半，在出售时获得更好的价格。\r\n高强度兵器：可以多操作一个自律炼金兵器，并且制作的自律炼金兵器增加一个2格初始生命压力。');
INSERT INTO `mfdata` VALUES ('302', '魔药', '额外技能', '魔药术致力于研究各种魔法药剂的作用，对魔药师来说，研究各种组合的材料组合而成的魔药效果是他们最开心的事。第二世代的魔药师的配方大多是实验而出，也有偶然发掘到的上世代魔药配方，这种一般能在黑市拍到高价。\r\n魔药术能炼制各种各样的魔法素材来提供帮助，魔药等级越高越能知晓更为稀有的配方，并且有炼制更为复杂魔药的能力，但一些配方仍然需要获得后才能炼制。\r\n等级.描述\r\n1.能在辨认常见魔药素材：\r\n2.炼制简单的魔药，如小魔力恢复剂。\r\n3.炼制更为复杂的魔药，如能短暂增强能力的药剂。\r\n4.可以解读前世代的魔药配方。\r\n5.熟知魔药素材的能力，并且能尝试组合新的魔药。\r\n6.熟知第二世代所有魔药炼制方法的，立于魔药师顶点的人。\r\n预设特技：\r\n魔药节约：使用魔药时可进行一次命运点投掷，当点数为3，4，-3，-4时，可以不消耗魔药（但下次使用同一瓶魔药必定消耗）。\r\n大量炼药：相同素材魔药产出提高，效用不变。');
INSERT INTO `mfdata` VALUES ('303', '魔识', '额外技能', '魔识指代魔法相关的知识，包括魔法本身，魔法阵以及一些魔法的传说。是学习魔法必须的技能。\r\n高等级的魔识能帮助更好的学习并使用法术，在进行魔法阵的构建时必须要一定程度的魔识。\r\n等级.描述\r\n1.粗略了解魔法的人\r\n2.有一定魔法的理论基础，可以刻画简单的法阵或破解简单的法阵，如生火阵。\r\n3.了解大部分魔法的运作机制，可以刻画或破解更难的魔法阵，如触炎阵。\r\n4.可以自由组合常见法阵，创建新的效果的法阵。\r\n5.可以尝试解读上世代魔法阵，并且还原部分还原。\r\n6.对上世代的法阵进行解读并破解，对本世代的法阵基本都了如指掌的人。\r\n预设特技\r\n加密法阵：敌人破解你的法阵需要克服的难度+2，用法阵创造优势时获得+2。\r\n解阵术士：破解法阵时魔识+2，改写法阵时魔识+1。');
INSERT INTO `mfdata` VALUES ('304', '精妙', '额外规则', '当骰点为指定格式时会触发的效果。如释放火弹术投出-1,1,1,1 即符合x1x1的格式。');
INSERT INTO `mfdata` VALUES ('401', '过载', '额外规则', '带有过载效果的法术可以在释放时消耗更多的法术来根据过载栏的记述获得额外的效果。');
INSERT INTO `mfdata` VALUES ('402', '专精', '额外规则', '当角色有专精属性且专精属性与释放法术的属性一致时会触发的效果。');
INSERT INTO `mfdata` VALUES ('501', '灼烧', '异常状态', '打斗下降1，每回合开始时受到（1+灼烧持续回合数）的伤害。');
INSERT INTO `mfdata` VALUES ('502', '束缚', '异常状态', '无法进行需要身体行动的技能，失去武器效果的加成。');
INSERT INTO `mfdata` VALUES ('503', '触电', '异常状态', '运动-1，受到电属性伤害+1，如果防具是导电金属，那么受到电属性伤害再+1.');
INSERT INTO `mfdata` VALUES ('504', '击飞', '异常状态', '将对象本回合的运动变为-2，打断其准备，吟唱和维持动作。');
INSERT INTO `mfdata` VALUES ('505', '迟缓', '异常状态', '目标运动-2，被赋予这个异常状态的回合行动顺序变为最后。');
INSERT INTO `mfdata` VALUES ('601', '专精', '设定', '当法师们对某种元素的尤其青睐，并且自身也有深入学习这项法术的资质时，可以尝试对其进行专精。\r\n专精并不是能一蹶而就的事情，一般需要长期处于该系元素充盈的地带并且尝试用自己的本源魔力与元素魔力进行结合。该系元素天赋越高越容易完成专精。\r\n专精会给该系元素法术带来显而易见的好处，但是在释放其他系元素法术时会获得魔力消耗翻倍的惩罚。\r\n在历史上仅有极其稀少的人能够专精两个属性， 因为专精第二个属性的难度远高于第一个时的难度。\r\n无属性无法被专精，专精之后也不会对无属性魔法的释放造成影响。');
INSERT INTO `mfdata` VALUES ('602', '魔力污染', '设定', '魔力污染是仅存在于大荒漠的现象，大荒漠中独特的魔力会侵蚀人的生命魔力,在一开始时，魔力污染会使人感觉恶心，乏力乃至晕眩，当维持身体完整的生命魔力被侵蚀到一定地步，身体无法继续保持形体，最终会化为魔力而崩溃。现在一般将大荒漠按照魔力污染分为4级。\r\nD级：无魔力污染的地区，此地区可以正常生活，区域内也长有许多荒漠性植物。\r\nC区：魔力污染轻微的地区。魔法实力与体质较差的普通人无法长期在此生存，而对有实力的人影响轻微。\r\nB区：魔力污染较为严重的地区，即使是充分做好准备的魔法师也会有非常明显的不适感，不建议在此区域停留超过一天。\r\nA区：魔力污染极为严重的地区，仅仅是呆在这个区域内就能显著的感受到生命魔力的流逝以及伴随带来的各种负面影响。即使做过完整的准备，人类也无法在A区活动超过两个小时。在大荒漠冒险时一般会选择避开污染等级为A的区域。\r\n');
INSERT INTO `mfdata` VALUES ('701', '卡托亚沙国', '地区/国家', '    卡托亚沙国的国土大部分由未被污染的大荒漠南方外围的沙漠所组成，境内只有唯一一条河流‘土河’。根据其历史记载在第二世代元年之前，卡托亚沙国的居民们就居住在沙漠之间的绿洲。长期居住在沙国的居民也因为环境的缘故，肤色变为古铜色，被称为沙民。\r\n    沙国虽然环境较为恶劣，但由于其境内有不少魔晶石矿与一些其他稀有矿藏，只有沙漠才存在的珍惜草药与稀有的魔兽素材，沙国得以从贸易中获得的利润作为立国之本。\r\n    在大约第二世代一世纪六十年代，其中一个沙漠部族的首领卡托亚统一了大荒漠南边的所有沙漠部族，建立了卡托亚沙国。卡托亚虽然军事天赋过人但为人骄奢淫逸喜爱享受，使用战争中获取的奴隶为其修建了庞大的卡托亚城。由于其毫无人道的压榨致使卡托亚城修建完毕时，奴隶工死亡率达到了七成。\r\n    卡托亚在卡托亚城修建完毕之后不久就病毙，由其弟托德即位。即位时国内各族爆发了大叛乱，虽然在其强力镇压下将叛乱扑灭，但也让沙国开始了百年以上的地方与中央对抗的历史。\r\n    第二世代429年，王室的旁支萨鲁赫家族的科萨在国内大贵族们的支持下，推翻了沙国王室并自己坐上了沙王的位置。为了维持大贵族的支持，科萨赋予了地方大贵族更多的权利，包括地方有权自行制定法律与扩展领内军队不再需要向沙王请示等，王家的权利进一步削弱。\r\n    第二世代650年，艾诺利亚对沙国与拉斯特王国发动征服战争，负责入侵沙国的是艾诺利亚的名将，国王的异母弟弟拉尔克。期初的沙国完全无法抵抗训练有素的艾诺利亚军，仅仅两个月国土就沦陷了接近一半，原沙王卡托亚十三世也死在了战场上，其十九岁的儿子阿利亚临时登基，依靠卡托亚城高耸的城墙与沙漠地形的游击骚扰拖延住了拉尔克的军队，双方进入了僵持阶段。651年由于艾诺利亚深陷耻辱战争，只得召回两只远征军。拉尔克拒绝了召回并且急于与沙国军决战求胜。阿利亚利用了这个心理利用沙暴与流沙击败了拉尔克的军队，迫使其归还沙国所有被侵占的领土并且用俘虏换取了一大笔赎金。\r\n    阿利亚雄心壮志的想改善沙国目前存在的问题，但在其视察被占领地区时被刺客所暗杀。有人说是大贵族们惧怕阿利亚会依靠自己的名望削弱他们的权利，有人说这是艾诺利亚残兵的复仇。但无论如何，沙国在此之后国力逐步衰落。\r\n    第二世代849年，沙王萨尔图四世的妻子爱莲娜·图乌城中坠亡。图乌公爵因此到卡托亚城“兴师问罪“。对大贵族早已非常不满的萨尔图四世秘密安排人想控制诺里斯公爵。但被公爵所识破，在混乱之中萨尔图四世在乱军之中被杀。这场混乱逐步演变成了在卡托亚的屠杀与掠夺，七百年历史的卡托亚城也被烧成了一座废城。事件发生后全国哗然，但一些嗅觉的贵族立刻意识到这是扩张的机会，沙国的内乱从此开始。');
INSERT INTO `mfdata` VALUES ('1001', '烟雾术*', '法术(无)', '魔力消耗：1\r\n属性：无\r\n类型:环境改变-持续型\r\n持续时间：两回合\r\n过载：增加一回合持续时间/1魔力\r\n效用：使用这个魔法时，进行一次魔法检定来以自身为圆心产生烟雾，\r\n强度根据最终值/2（进位）决定：如下。\r\n1：烟雾范围内敌人在攻击时打斗/射击 -1\r\n2：烟雾范围内敌人在攻击时打斗/射击 -2\r\n3：除2的效果外，在烟雾内的友方在没被探测到时无法成为对方的攻击目标。\r\n4：除3的效果外，释放烟雾术的人获得一个优势与免费援引\r\n5：除4的效果外，在烟雾内的友方也获得免费援引。\r\n援护：这个魔法可以在自己或友方成为被攻击的目标时使用，此时如果烟雾术的\r\n魔法最终值大于进攻方技能最终值时可以无效伤害，使用后下次自己无法进行\r\n行动。\r\n');
INSERT INTO `mfdata` VALUES ('1002', '反射术', '法术(无)', null);
INSERT INTO `mfdata` VALUES ('1003', '干扰术*', '法术(无)', '魔力消耗：2\r\n属性：无\r\n类型: 干扰型\r\n效用：在对方使用魔法时，使用魔法与对方进行对抗，检定结果高于对方1以上时可以无效对方的魔法，并且给予对方对方的魔法技能等级的魔法伤害。干扰术一天只能使用一次。\r\n');
INSERT INTO `mfdata` VALUES ('1004', '治愈术', '法术(无)', '魔力消耗：1\r\n属性：无\r\n类型: 回复型\r\n效用：以对象的中度及以下的后果或者一个生理压力槽为目标释放，并根据后果或者压力槽来决定需要过的克服。\r\n压力槽：这个压力槽所能承载的最大压力（如4槽就为+4克服）\r\n轻微后果：+3克服\r\n中度后果：+5克服\r\n治愈术及同类法术对同一个对象一天只能使用一次、');
INSERT INTO `mfdata` VALUES ('1101', '风羽术', '法术(风)', '魔力消耗：2\r\n属性：风（70%）\r\n类型:增益型-能力提升\r\n过载：持续回合增加1/1魔力\r\n效用：自己的运动上升魔法检定/2（进位），并且选择两名队友获得这个效果的一半（进位），持续三回合。\r\n专精：这个魔法可以在回合开始时就使用，但魔力消耗增加1');
INSERT INTO `mfdata` VALUES ('1102', '风刃术', '类型:法术(风)', '魔力消耗：1\r\n属性：风（60%）\r\n类型:伤害型-魔法伤害\r\n过载：伤害增加2/1魔力\r\n效用：使用魔法与对方的防御动作进行对抗，给予超过部分的魔法伤害。\r\n专精：当自身运动高于对方时，风刃术的伤害上升运动差值的一半（进位）\r\n精妙：（75以上天赋可用）当投掷出1xx1（x代表任意），给对方附加直到下回合结束的迟缓状态');
INSERT INTO `mfdata` VALUES ('1103', '风牢术', '法术(风)', '魔力消耗：2\r\n维持魔力：1\r\n属性：风（70%）\r\n类型:异常附加-维持型\r\n效用：使用四面八方的风墙束缚对方，法术释放成功时可以将对方束缚。每回合开始时进行一次魔力对抗。如果自身胜出，给予超过部分的风属性魔法伤害。如果对方胜出则对方挣脱束缚。维持此法术期间无法进行其他行动。\r\n专精：每回合开始时的魔力对抗如果平手，则自身魔力检定最终值+1。');
INSERT INTO `mfdata` VALUES ('1104', '风压术', '法术(风)', '魔力消耗：1\r\n属性：风（60%）\r\n过载：魔法检定最终值+1/1魔力\r\n类型:异常附加\r\n效用：选择一个物体释放。使用自身的魔法与目标的体魄进行对抗（如果是场景物件则为对应重量的克服）。成功后可以将其击飞（当前回合运动变为-2，如果目标正在进行施术吟唱，这个异常会打断吟唱）。这个法术可以被护盾型法术防御。\r\n专精：施术成功时可以额外多消耗一魔力，跳过目标的下次行动。');
INSERT INTO `mfdata` VALUES ('1105', '风息盾', '法术(风)', '魔力消耗：1\r\n属性：风（65%）\r\n类型:护盾型-持续型\r\n过载：护盾持续时间增加1回合/1魔力\r\n效用：使用这个魔法会在施术者正前方产生一个疾风护盾，血量为魔法检定最终值，疾风护盾会代替友方承受魔法伤害直至超过持续时间（三回合）或被击破。当受到远程投射攻击，且疾风护盾剩余生命值大于对方射击检定的最终值时，可以将攻击捕获。在自己行动时如果护盾依然存在，可以消耗1魔力将捕获到的投射攻击全部反射。（对部分特殊投射武器无效）\r\n专精：疾风护盾现在可以抵御物理攻击，但物理攻击对护盾造成的伤害+2.施术者可以自行决定是否抵御。\r\n援护：这个魔法可以在自己或友方成为被攻击的目标时使用，但持续时间变为只持续到本回合结束。');
INSERT INTO `mfdata` VALUES ('1106', '飓风术', '法术(风)', '魔力消耗:3\r\n吟唱时间:1\r\n维持魔力：1\r\n属性：风（80%）\r\n类型:伤害型-异常附加-持续型-吟唱\r\n效用:召唤出强度为魔法检定最终值的飓风并控制，每回合可以选择互相站位在五米内的三个目标使飓风攻击。对方必须使用防御动作技能进行飓风强度的克服，如果克服失败会受到差值的魔法伤害并且被击飞。在维持飓风术期间无法进行其他的动作。当施术者受到伤害时，此法术会被打断。\r\n专精：除非施术者受到中等后果以上的伤害，否则飓风术不会因为伤害而被打断。');
INSERT INTO `mfdata` VALUES ('1201', '炎气盾术', '法术(火)', '魔力消耗：1\r\n属性：火（65%）\r\n类型:护盾型-持续型\r\n过载：护盾持续时间增加1回合/1魔力\r\n效用：使用这个魔法会在施术者正前方产生一个燃烧护盾，血量为魔法检定最终值，燃烧护盾会代替友方承受魔法伤害直至超过持续时间或被击破。被护盾保护的人收到近战伤害时可以对造成伤害的人给予护盾剩余生命值/2（进位的魔法伤害）。\r\n专精：燃烧护盾剩余生命值大于对方射击检定的最终值时，可以无效对方的投射攻击（对部分特殊投射武器无效）\r\n援护：这个魔法可以在自己或友方成为被攻击的目标时使用，但持续时间变为只持续到本回合结束。');
INSERT INTO `mfdata` VALUES ('1202', '火弹术', '法术(火)', '魔力消耗：1\r\n属性：火（60%）\r\n类型:伤害型-魔法伤害\r\n过载：伤害增加2/1魔力\r\n效用：使用魔法与对方的防御动作进行对抗，给予超过部分的魔法伤害。\r\n专精：如果火弹术给对方造成了伤害，那么下次该单位受到的火属性魔法伤害+2\r\n精妙：（75以上天赋可用）当投掷出x1x1（x代表任意），额外造成2火属性魔法伤害。');
INSERT INTO `mfdata` VALUES ('1203', '火球术', '法术(火)', '魔力消耗：2\r\n属性：火（70%）\r\n类型:伤害型-魔法伤害-范围伤害\r\n-过载：增加一个附加单位/1魔力\r\n效用：选择一个主要单位和与主要单位站位接近的两个附加单位进行基础魔法攻击，这个魔法对附加单位造成的伤害-1.此时如果对方使用运动防御,运动检定在计算最终值时-1。\r\n专精：火球术可以不选择额外单位，这时候对主要单位造成魔法伤害+3\r\n精妙：（80以上天赋可用）当投掷出x1x1（x代表任意），附加单位伤害不再下降，对主要单位伤害+1|');
INSERT INTO `mfdata` VALUES ('1204', '燃烧武器', '法术(火)', '魔力消耗：1\r\n属性：火（65%）\r\n类型:属性赋予-持续型\r\n过载：增加一回合持续时间/1魔力\r\n效用：给友方一个单位的武装附加2回合的炎属性，使用该武装攻击时额外造成魔法检定最终值/3（进位）的伤害。\r\n专精：如果攻击目标带有护甲，在攻击成功后直到下回合结束护甲下降1.\r\n精妙：（75以上天赋可用）当投掷出x1x1（x代表任意），释放这个法术后可以立刻进行攻击。');
INSERT INTO `mfdata` VALUES ('1205', '炎狱术*', '法术(火）', '魔力消耗：2\r\n属性：火（70%）\r\n类型:异常赋予-限制行动-稀有法术\r\n效用：使用魔法给敌人的防御动作进行对抗，根据超过的值给予以下效果\r\n1.给目标附着持续到下回合结束的灼烧状态\r\n2.在1效果的基础上，将目标下次的行动顺序置于最后。\r\n3及以上.在2效果的基础上，再给目标附加禁锢效果。\r\n专精：对魔法为2及以下的生物释放成功直接应用第三个效果。\r\n');
INSERT INTO `mfdata` VALUES ('1206', '烈焰长枪', '法术(火）', '魔力消耗：2\r\n吟唱时间：1\r\n属性：火（75%）\r\n类型:伤害型-魔法伤害\r\n过载：伤害增加2/1魔力\r\n效用：使用魔法与对方的防御动作进行对抗，给予超过部分的魔法伤害并附加直到下回合结束的灼烧效果，这个攻击无视魔抗。\r\n专精：给予+2火属性魔法伤害。');
INSERT INTO `mfdata` VALUES ('1301', '电击术', '法术(电）', '魔力消耗：1\r\n属性：电（60%）\r\n类型:伤害型-魔法伤害\r\n过载：伤害增加2/1魔力\r\n效用：使用魔法与对方的防御动作进行对抗，给予超过部分的魔法伤害。\r\n专精：如果上回合使用雷击造成了伤害，会对敌人造成持续到下回合结束的触电\r\n精妙：（75以上天赋可用）当投掷出11xx（x代表任意），造成额外两点魔法伤害。');
INSERT INTO `mfdata` VALUES ('1302', '闪电链术', '法术(电）', '魔力消耗：2\r\n属性：电(75%）\r\n类型:伤害型-魔法伤害-范围伤害\r\n过载：增加1伤害/1魔力\r\n效用：选择一个主要单位和与主要单位站位接近的两个附加单位进行基础魔法攻击，这个魔法先对主要目标造成伤害然后产生电束攻击其他人，攻击附加时伤害-1。此时如果对方使用运动防御,运动检定在计算最终值时-1。\r\n专精：闪电链不会产生电束,但对敌人造成持续两回合的触电。\r\n精妙：（80以上天赋可用）当投掷出x1x1（x代表任意），下次受到的电属性伤害+2');
INSERT INTO `mfdata` VALUES ('1303', '电场术*', '法术(电）', '魔力消耗：2\r\n维持魔力：1\r\n属性：电（70%）\r\n类型:维持型-领域型-稀有法术\r\n效用：以自身周围五米为范围，对五米内的目标使用电属性伤害魔法投掷结果+3，之后造成的伤害-2.\r\n专精：将范围扩大为十米\r\n');
INSERT INTO `mfdata` VALUES ('1304', '电流术*', '法术(电)', '魔力消耗：2\r\n属性：电(75%)\r\n类型:伤害型-稀有法术 \r\n效用：选择两个站位接近的目标，给予基础魔法攻击，这个攻击无法被运动所回避。选择的两个目标需要在两个回合以内被电属性伤害伤害过。\r\n专精：如果电流术选择的两个目标处于电场术的范围内，造成的伤害+2\r\n精妙：（80以上天赋可用）当投掷出11xx（x代表任意），给予目标持续两个回合的触电效果并将两个目标下次行动顺序置为回合最后。 \r\n\r\n');
INSERT INTO `mfdata` VALUES ('1305', '闪电枷锁', '法术(电)', '魔力消耗 2\r\n维持消耗：1\r\n类型:维持型-异常附加-控制型\r\n属性：电(75%）\r\n效用：将你与60码内的一个敌人用电链连接起来并形成正负极，对方受到触电效果并且运动额外下降一，每回合固定给予1电属性伤害，\r\n专精:施法成功的回合可以额外消耗1魔力跳过对方的下次行动。');
INSERT INTO `mfdata` VALUES ('1306', '轰雷术', '法术(电)', '法力消耗:3\r\n吟唱时间:1\r\n属性：电(80%）\r\n过载:2伤害/1魔力\r\n效用：对单个目标造成魔法+7的伤害，目标在闪避时需承受运动-1的影响\r\n专精:当轰雷术造成伤害时，可以选择目标再次造成电属性伤害（等价于释放一次电击术）。\r\n精妙:（85以上天赋可用）当投掷出x1x1（x代表任意），在攻击命中目标即可对目标造成持续到下回合结束的触电效果。');
INSERT INTO `mfdata` VALUES ('1401', '冰盾术', '法术(水)', '魔力消耗：1\r\n过载：护盾增加2生命值/1魔力\r\n效用：使用这个魔法会在正前方生成一个冰盾，血量为魔法检定最终值，冰盾会代替友方单位承受伤害直到超过持续时间或被击破。\r\n专精：生成的冰盾获得1物理护甲与1魔法护甲\r\n援护：这个魔法可以在自己或友方成为被攻击的目标时使用，但持续时间变为只持续到本回合结束。\r\n');
INSERT INTO `mfdata` VALUES ('1402', '冰锥术', '法术(水)', '魔力消耗：1\r\n属性：水（60%）\r\n类型:伤害型-魔法伤害\r\n效用：使用魔法与对方的防御动作进行对抗，给予超过部分的魔法伤害。\r\n专精：如果上回合使用冰锥术且支付了魔力，那么下一次使用冰锥术不消耗魔力。\r\n精妙：（75以上天赋可用）当投掷出1xx1（x代表任意），造成额外两点物理伤害。\r\n');
INSERT INTO `mfdata` VALUES ('2001', '沾衣十八跌', '战技（无）', '魔力消耗：1\r\n属性：无（60%）\r\n类型:伤害型-妨碍型\r\n效用：来自东大陆的武术，使用打斗与对手的防御技能进行 对抗，这次打斗的检定最终值下降1。如果这次打斗造成伤害，可以通过战斗手法与内劲将敌人击倒，让敌人失去一次行动。');
INSERT INTO `mfdata` VALUES ('2301', '雷电波动', '战技（电）', '魔力消耗：1\r\n属性：电（60%）\r\n类型:伤害型-魔法伤害-削弱型\r\n效用：将魔力通过武器直接释放，将打斗攻击转化为电属性的魔法伤害。命中直到下回合结束降低对方运动2.');

-- ----------------------------
-- Table structure for `mfuser`
-- ----------------------------
DROP TABLE IF EXISTS `mfuser`;
CREATE TABLE `mfuser` (
  `key` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userID` varchar(20) DEFAULT NULL,
  `userName` varchar(40) DEFAULT NULL,
  `userOldName` varchar(40) DEFAULT NULL,
  `usergroup` varchar(20) DEFAULT NULL,
  `userAttribute` varchar(255) DEFAULT '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|',
  `userData` text,
  `userItem` text,
  `userEquip` varchar(255) DEFAULT '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|',
  PRIMARY KEY (`key`),
  KEY `userName` (`userName`),
  KEY `userID` (`userID`),
  KEY `usergroup` (`usergroup`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mfuser
-- ----------------------------
INSERT INTO `mfuser` VALUES ('00000073', '445630541', '风林戈', '风林戈', '650903307', '魔法:4|魔识:3|魔药:2|炼金:0|运动:0|窃术:0|人脉:1|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:3|调查:1|学识:0|察觉:2|体魄:1|挑拨:0|亲善:0|资源:0|射击:0|潜匿:1|意志:2|魔力亲和:5|体魄X:-1|', '命运点:2|魔力压力:7|经济压力:3|生理压力:□1□2|心理压力:□1□2□3|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000074', '553859318', '格赫罗斯', '盘尼西林不会过敏', '781968580', '魔法:4|魔识:3|魔药:2|炼金:0|运动:0|窃术:0|人脉:1|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:3|调查:1|学识:0|察觉:2|体魄:1|挑拨:0|亲善:0|资源:0|射击:0|潜匿:1|意志:2|魔力亲和:5|体魄X:-1|', '命运点:2|魔力压力:7|经济压力:3|生理压力:□1□2|心理压力:□1□2□3|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000075', '312984729', '卡尔萨斯 科穆宁', 'Constantinopoli,', '781968580', '魔法:4|魔识:3|魔药:0|炼金:0|运动:1|窃术:0|人脉:1|工艺:0|欺骗:1|驾驶:0|移情:0|打斗:0|调查:2|学识:3|察觉:2|体魄:0|挑拨:0|亲善:0|资源:2|射击:0|潜匿:0|意志:1|魔力亲和:6|体魄X:-2|魔法X:1|', '命运点:3|魔力压力:10|经济压力:6|生理压力:□1□2|心理压力:□1□2□3|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000076', '39638310', 'SeeD', 'SeeD', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', '火元素:35%|风元素:57%|雷元素:64%|地元素:87%|水元素:72%|金元素:14%|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000077', '553859318', '盘尼西林不会过敏', '盘尼西林不会过敏', '650903307', '魔法:4|魔识:3|魔药:2|炼金:0|运动:0|窃术:0|人脉:1|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:3|调查:1|学识:0|察觉:2|体魄:1|挑拨:0|亲善:0|资源:0|射击:0|潜匿:7|意志:2|魔力亲和:5|体魄X:-1|', '命运点:2|魔力压力:7|经济压力:3|生理压力:□1□2|心理压力:□1□2□3|', '幼鸽-199-20:51|幼鸽2-1-0:51|', '武器1:无|武器2:命啊|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000078', '826037341', '早起的虫儿被鸟吃', '早起的虫儿被鸟吃', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000079', '491823948', '艾瑞尔', '孤单的魔法师', '781968580', '魔法:4|魔识:3|魔药:2|炼金:0|运动:0|窃术:0|人脉:1|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:3|调查:1|学识:0|察觉:2|体魄:1|挑拨:0|亲善:0|资源:0|射击:0|潜匿:1|意志:2|魔力亲和:5|体魄X:-1|', '命运点:2|魔力压力:7|经济压力:3|生理压力:□1□2|心理压力:□1□2□3|', '轻伤药-1-0:2|魔力药剂-1-0:1|回复药-1-0:1|', '武器1:铁剑(伤害+1）|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000080', '1365976704', '高冷喵??', '高冷喵??', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', '火元素:49%|风元素:72%|雷元素:65%|地元素:63%|水元素:72%|金元素:17%|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000081', '759847221', '望洋兴叹', '望洋兴叹', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', '火元素:35%|风元素:64%|雷元素:55%|地元素:50%|水元素:65%|金元素:15%|', null, '武器1:魔杖|武器2:无|防具:附魔披风(1物抗1魔抗)|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000082', '274799849', 'setaceus', 'setaceus', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', '火元素:62%|风元素:62%|雷元素:72%|地元素:57%|水元素:57%|金元素:12%|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000083', '1169063282', '阿什利·尤里乌斯', '咿呀', '781968580', '魔法:4|魔识:3|魔药:0|炼金:0|运动:1|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:3|察觉:1|体魄:2|挑拨:0|亲善:1|资源:2|射击:0|潜匿:0|意志:2|魔力亲和:6|体魄X:-2|魔法X:1|', '命运点:3|魔力压力:10|经济压力:6|生理压力:□1□2|心理压力:□1□2□3|', null, '武器1:魔杖|武器2:无|防具:附魔披风(1物抗1魔抗)|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000084', '3029953161', '空酱', '空酱', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000085', '2476750600', 'Gasai Yuno', 'Gasai Yuno', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000086', '3203853179', '优酱', '优酱', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', '火元素:78%|风元素:55%|雷元素:84%|地元素:70%|水元素:55%|金元素:12%|', null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000087', '75296158', '过氧化氢', '过氧化氢', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000088', '875985913', '日华', '日华', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000089', '1738212350', '一念三千', '一念三千', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000090', '2516840270', '四季のフラワーマスター', '四季のフラワーマスター', '781968580', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000091', '553859318', '盘尼西林不会过敏', '盘尼西林不会过敏', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000092', '1738212350', '一念三千', '一念三千', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000093', '312984729', 'Constantinopoli,', 'Constantinopoli,', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000094', '3203853179', '优酱', '优酱', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000095', '747069596', '夙玖怜(骰性恋)', '夙玖怜(骰性恋)', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000096', '875985913', '日华', '日华', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000097', '759847221', '望洋兴叹', '望洋兴叹', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000098', '3029953161', '空酱', '空酱', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');
INSERT INTO `mfuser` VALUES ('00000099', '826037341', '早起的虫儿被鸟吃', '早起的虫儿被鸟吃', '790664778', '魔法:0|魔识:0|魔药:0|炼金:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|驾驶:0|移情:0|打斗:0|调查:0|学识:0|察觉:0|体魄:0|挑拨:0|亲善:0|资源:0|射击:0|潜匿:0|意志:0|魔力亲和:4|', null, null, '武器1:无|武器2:无|防具:无|饰品1:无|饰品2:无|');

-- ----------------------------
-- Table structure for `qquser`
-- ----------------------------
DROP TABLE IF EXISTS `qquser`;
CREATE TABLE `qquser` (
  `key` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `userID` varchar(20) DEFAULT NULL,
  `userName` varchar(40) DEFAULT NULL,
  `userOldName` varchar(40) DEFAULT NULL,
  `usergroup` varchar(20) DEFAULT NULL,
  `userAttribute` varchar(255) DEFAULT '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|',
  `userData` text,
  `userItem` text,
  `userEquip` varchar(255) DEFAULT '',
  PRIMARY KEY (`key`),
  KEY `userName` (`userName`),
  KEY `userID` (`userID`),
  KEY `usergroup` (`usergroup`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qquser
-- ----------------------------
INSERT INTO `qquser` VALUES ('00000010', '1147580553', 'Frank', 'Frank', '790664778', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', 'Date:2019-07-25|', null, '');
INSERT INTO `qquser` VALUES ('00000012', '553859318', 'GM', '盘尼西林不会过敏', '790664778', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', 'Date:2019-07-25|', null, '');
INSERT INTO `qquser` VALUES ('00000013', '2543957898', '墜落のゆうしゃ', '墜落のゆうしゃ', '790664778', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', 'Date:2019-07-25|', null, '');
INSERT INTO `qquser` VALUES ('00000014', '1738212350', '一念三千', '一念三千', '790664778', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', 'Date:2019-07-25|', null, '');
INSERT INTO `qquser` VALUES ('00000015', '553859318', 'BBGM', '盘尼西林不会过敏', '650903307', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', 'Date:2019-07-25|', null, '');
INSERT INTO `qquser` VALUES ('00000016', '445630541', '风林戈', '风林戈', '650903307', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000017', '747069596', '夙玖怜( lugroma)', '夙玖怜( lugroma)', '790664778', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000018', '1832415966', 'denpa', 'denpa', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000019', '1738212350', '一念三千', '一念三千', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000020', '1283370592', '土拨鼠拨土', '土拨鼠拨土', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000021', '553859318', '盘尼西林不会过敏', '盘尼西林不会过敏', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000022', '312984729', 'Constantinopoli,', 'Constantinopoli,', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000023', '1779034895', '少年郎呦', '少年郎呦', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000024', '2516840270', '四季のフラワーマスター', '四季のフラワーマスター', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000025', '958683584', '童谣世界第一可爱', '童谣世界第一可爱', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000026', '512386654', '月夜下的坠歌', '月夜下的坠歌', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
INSERT INTO `qquser` VALUES ('00000027', '747069596', '夙玖怜( lugroma)', '夙玖怜( lugroma)', '618409993', '感应:0|亲善:0|意志:0|挑拨:0|运动:0|窃术:0|人脉:0|工艺:0|欺骗:0|射击:0|移情:0|打斗:0|调查:0|学识:0|资源:0|体魄:0|工程:0|电子:0|驾驶:0|机师等级:1|', null, null, '');
