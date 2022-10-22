SELECT
 pr.*
FROM
 v3_delay_wms_data d
 inner JOIN sp_procurement_shipping_rel rel on d.shipping_order_id=rel.shipping_order_id
 inner JOIN v3_procurement_order_line line on line.procurement_order_line_id=rel.procurement_order_line_id
 inner JOIN v3_procurement_message_recevied pr on pr.business_id=d.business_id
 WHERE d.`status`=1 and line.fsm_state=5 and d.create_time>'2021-02-24 00:00:00' and pr.recieve_time>'2021-02-24 00:00:00'
 group BY pr.message_id;
 
 SELECT
 d.shipping_order_id,line.procurement_order_id,line.sku,line.quantity,line.bar_code from
 v3_delay_wms_data d
 inner JOIN sp_procurement_shipping_rel rel on d.shipping_order_id=rel.shipping_order_id
 inner JOIN v3_procurement_order_line line on line.procurement_order_line_id=rel.procurement_order_line_id
 WHERE d.`status`=1 and line.fsm_state=5 and d.create_time>'2021-02-24 00:00:00' 
 group by line.procurement_order_line_id
 order by d.shipping_order_id desc;
 
 


 
 SELECT * FROM v3_delay_wms_data WHERE shipping_order_id=3302437 and sku='S67796470XLV3' and procurement_order_id=18451483
 SELECT * FROM v3_shipping_diff_detail WHERE shipping_order_id=3455595
  
 SELECT * FROM v3_delay_wms_data WHERE `status`=1 and create_time>'2021-02-24 00:00:00' and type=4 ;
 -- myth 
    SELECT * FROM myth_producer_message_content WHERE message_content like '%3584352%' and create_ts>'2021-05-10 00:00:00'
    select * from v3_procurement_message_recevied where message like '%19091947%' and message like '%N000293800002%' and recieve_time between '2021-05-01 00:00:00' and '2021-06-01 00:00:00';

SELECT * FROM myth_producer_message_content WHERE message_content like '%19091947%' and  message_content like '%N000293800002%' and create_ts between '2021-05-10 00:00:00' and '2021-06-01 00:00:00';


SELECT c.id,c.create_ts,c.queue_name,c.topic,c.message_status,co.message_content FROM myth_consumer_message c left join myth_consumer_message_content co on co.consumer_id=c.id WHERE   co.create_ts between '2022-06-01 00:00:00' and '2022-07-01 00:00:00' and c.message_status!=500 and c.queue_name='v2_pms_ProcurementWarehouseMythListener_wareHouseProcurementHandler';
 
select
count(distinct l.procurement_order_line_id)
from
v3order_fsm_instance fsm
join v3_procurement_order_line l on fsm.id = l.fsm_instance_id
where
l.fsm_state = 5
and fsm.enterTime < date_add(now(), interval -2 day);

-- 执行中
update v3_procurement_order_line set fsm_state=4 WHERE procurement_order_line_id=103840221
UPDATE v3order_fsm_instance set state='EXECUTE',enterTime=NOW(),`owner`='System',role='EXECUTE',description='回退至执行中' WHERE id=459686266

-- 有现货代发
UPDATE v3_procurement_order_line set supplier_confirm_state=1,supplier_confirm_time=now() WHERE procurement_order_line_id in(103894363,103894382,103894653,104190115,104183592,104535899);

-- 模板
SELECT
	rel.shipping_order_id,
	line.procurement_order_id,
	line.sku,
	line.quantity,
	line.bar_code 
FROM
	v3_procurement_order_line line
	LEFT JOIN sp_procurement_shipping_rel rel ON line.procurement_order_line_id = rel.procurement_order_line_id 
WHERE
	line.procurement_order_line_id IN ( 18638166 ) 
	LIMIT 100;


-- 备货单行是否生成了采购单
SELECT
distinct  so.id,line.sku
FROM
	v3_supplier su
	LEFT JOIN v3_stock_order_line line on su.supplier_no=line.supplier_no AND su.is_litb_barcode =0
	LEFT JOIN v3_stock_order so ON line.stock_order_id = so.id 
WHERE line.procurement_order_id is null and line.exception_message is null and so.`status`=2
order by so.id desc
-- 取消内采单（刷数据用）
select * from v3order_fsm_instance  WHERE object_id in(2956627) and template_id='InnerProcurementFlow';
-- 取消内采单
UPDATE v3_inner_procurement_order SET state=7 WHERE inner_procurement_order_id in(2956627);
UPDATE v3order_fsm_instance SET state='FINISHED',enterTime=NOW(),role='FINISHED',description='取消自动完成' WHERE id in(466519628);
-- 缺货取消内采单行
UPDATE v3_inner_procurement_order_line SET state=1 WHERE inner_procurement_order_line_id=4925533;
UPDATE v3order_fsm_instance SET state='CANCEL_OUT',enterTime=NOW(),role='CANCEL_OUT',description='取消自动完成' WHERE id in(466519656);

-- 采购单行重复发货

select * from sp_procurement_shipping_rel where procurement_order_line_id in(
select procurement_order_line_id from sp_procurement_shipping_rel rel where rel.update_time>'2021-05-01 00:00:00' and shipping_order_id is not null  group by rel.procurement_order_line_id,flag  having count(1)>1) order by procurement_order_line_id desc

--自动下单测试数据
UPDATE auto_order_log SET create_time=NOW() WHERE create_time BETWEEN '2021-05-17 00:00:00' and '2021-05-17 23:59:59';

UPDATE auto_order_record set date=NOW() WHERE date='2021-05-17';

UPDATE auto_order_task SET create_time=NOW() WHERE create_time BETWEEN '2021-05-17 00:00:00' and '2021-05-17 23:59:59';


-- 采购单行回退至执行中
update v3_procurement_order_line set fsm_state=4 WHERE procurement_order_line_id in(105718812,105718407,106990995,107169281);
UPDATE v3order_fsm_instance set state='Executing',enterTime=NOW(),`owner`='System',role='Executing',description='回退至执行中' WHERE id in( 464114317,464114993,466316912,466719320);
-- 采购单回退至执行中

update v3_procurement_order set fsm_state=4 WHERE procurement_order_id in(18860881,18860598,19207924,19275915);
UPDATE v3order_fsm_instance set state='Executing',enterTime=NOW(),`owner`='system',role='Executing',description='回退至执行中' WHERE id in( 464114240,464114928,466316894,466719260);

-- 添加负向流水

INSERT INTO `v3_supplier_paylist_journal` (`type`,`supplier_paylist_id`,`currencies_id`,`supplier_id`,`procurement_order_line_id`,`procurement_order_id`,`sku`,`pay_condition`,`account_type`,`mode`,`quantity`,`price`,`due_amount`,`cause_type`,`create_time`,`payable_time`,`pay_time`,`instock_confirmed_time`,`excuting_confirmed_time`,`payable_time_with_clearing_interval`,`state`,`is_returned`,`rate`,`commission_rate`,`commission_amount`) VALUES 
('0',null,'2','79556','106990995','19207924','EZ5022317100002','1','0','1','1','14','-12.6','0','2021-05-21 10:04:03','2021-05-21 10:04:03',null,null,null,null,'2',null,null,'0.1','-1.4'),
(null,null,'2','6066','107169281','19275915','S47670850000A','3','0','1','1','17','-17','2','2021-06-04 16:15:21','2021-07-21 16:15:21',null,'2021-06-06 16:15:21',null,'2021-07-27 16:15:21','0',null,null,'0','0');

