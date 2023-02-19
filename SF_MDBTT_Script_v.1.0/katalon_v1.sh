#!/bin/bash

#Script for activity call by Katalon to perform any DB related to MDBTT
#Created by galih rakasiwa 22 Feb 2021
#Last Modified add new case 22 Feb 2021
#Add 4 new case 5,6,7,8
echo "========Start MDBTT Script========="
case "$1" in
    #case 1
    "DeletePartition") echo "case 1 - Delete ALl Partition Except Regular Balance"
	echo "Query ===> delete from bal where acct_id=$2 and acct_res_id <> 1;"
	mdbcsql ocs/ocs@ocs <<-EOF
	delete from bal where acct_id=$2 and acct_res_id <> 1;
	y
	quit
	EOF
	break
	;;
    #case 2
    "DeleteRecurring") echo "case 2 - Delete Recurring Result"
	echo "Query1 ===> delete  from subs_acm_cycle where subs_id=$2;"
	echo "Query2 ===> delete  from subs_acm_daily where subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	delete from subs_acm_cycle where subs_id=$2;
	y
	delete from subs_acm_daily where subs_id=$2;
	y
	quit
	EOF
	break
	;;
    #case 3
    "DeleteACM") echo "case 3 - Delete ACM"
	echo "Query ===> delete from subs_acm where subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	delete from subs_acm where subs_id=$2;
	y
	quit
	EOF
	break
	;;
    #case 4
    "RemoveIPP") echo "case 4 - Remove IPP with delete partition, delete Recurring and delete ACM"
	echo "Query ===> 
	1. delete from bal where acct_id=$3 and acct_res_id <> 1;
	2. delete from subs_acm_cycle where subs_id=$2;
	3. delete from subs_acm_daily where subs_id=$2;
	4. delete from subs_acm where subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	delete from bal where acct_id=$3 and acct_res_id <> 1;
	y
	delete from subs_acm_cycle where subs_id=$2;
	y
	delete from subs_acm_daily where subs_id=$2;
	y
	delete from subs_acm where subs_id=$2;
	y
	quit
	EOF
	break
	;;
    #case 5
    "SelectPartition") echo "case 5 - Select All Partition "
	echo "Query ===> select * from bal where acct_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	select * from bal where acct_id=$2;
	quit
	EOF
	break
	;;
    #case 6
    "SelectSubsACMCycle") echo "case 6 - Select ACM cycle"
	echo "Query ===> select * from subs_acm_cycle where subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	select * from subs_acm_cycle where subs_id=$2;
	quit
	EOF
	break
	;;
    #case 7
    "SelectSubsACM") echo "case 7 - Select subs ACM"
	echo "Query ===> select * from subs_acm where subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	select * from subs_acm where subs_id=$2;
	quit
	EOF
	break
	;;
    #case 8
    "SelectSubsACMDaily") echo "case 6 - Select ACM daily"
	echo "Query ===> select * from subs_acm_daily where subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	select * from subs_acm_daily where subs_id=$2;
	quit
	EOF
	break
	;;
    #case 9
    "UpdateACMCycleValue") echo "case 9 - Update Value ACM Cycle per ACM Cycle Type ID"
	echo "Query ===> update acm_cycle set value=$3 where subs_id=$2 and acm_cycle_type_id=$4;"
	mdbcsql ocs/ocs@ocs <<-EOF
	update acm_cycle set value=$3 where subs_id=$2 and acm_cycle_type_id=$4;
	quit
	EOF
	break
	;;
    #case 10
    "UpdateSubsACMValue") echo "case 10 - Update Value ACM per Resource ID"
	echo "Query ===> update subs_acm set value=$4 where resource_id=$3 and subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	update subs_acm set value=$4 where resource_id=$3 and subs_id=$2;
	quit
	EOF
	break
	;;
    #case 11
    "SelectACMCycle") echo "case 10 - Update Value ACM per Resource ID"
	echo "Query ===> select * from acm_cycle where subs_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	select * from acm_cycle where subs_id=$2;
	quit
	EOF
	break
	;;
    #case 12
    "updatePartitionToZero") echo "case 12 - Update Value Partition per Bal ID"
	echo "Query ===> update bal set gross_bal=$3, consume_bal=$4, init_bal=$5 where bal_id=$2;"
	mdbcsql ocs/ocs@ocs <<-EOF
	update bal set gross_bal=$3, consume_bal=$4, init_bal=$5 where bal_id=$2;
	quit
	EOF
	break
	;;
    #case 13
    "DeletePartitionAcctResIDNotIn") echo "case 1 - Delete All Partition Except Selected Partition"
	echo "Query ===> delete from bal where acct_id=$2 and acct_res_id not in ($3);"
	mdbcsql ocs/ocs@ocs <<-EOF
	delete from bal where acct_id=$2 and acct_res_id not in ($3);
	y
	quit
	EOF
	break
	;;
    #case 14
    "UpdatePartitionBalance") echo "case 14 - Update Balance for Selected Partition"
	echo "Query ===> update bal set gross_bal=$4, consume_bal=$4, init_bal=$4 where acct_id=$2 and acct_res_id=$3;"
	mdbcsql ocs/ocs@ocs <<-EOF
	update bal set gross_bal=$4, init_bal=$4 where acct_id=$2 and acct_res_id=$3;
	y
	quit
	EOF
	break
	;;
    #case 15
    "FlexibleQuery") echo "case 15 - Flexible single query MDBTT"
	echo "Query ===> $2"
	mdbcsql ocs/ocs@ocs <<-EOF
	$2
	quit
	EOF
	break
	;;
    #case 16
    "FlexibleQueryConfirm") echo "case 16 - Flexible query MDBTT with Confirm"
	echo "Query ===> $2"
	mdbcsql ocs/ocs@ocs <<-EOF
	$2
	y
	quit
	EOF
	break
	;;

    #default
    *) echo "Command: sh katalon_v1.sh <case/activity> <param/MDN> <param2> <param-n>"
	;;
esac
#echo "=========End MDBTT Script=========="
