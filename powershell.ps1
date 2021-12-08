#Example #1 – Get the list of available event logs on the local computer

#Get-EventLog -List

#Example #2 – Get System Log on the local computer

#Get-EventLog -LogName System

#Example #3 – Get the most recent 10 entries from System log

#Get-EventLog -LogName System -Newest 10

#Example #4 – Get local system log on a certain day

#Get-EventLog -LogName System -After "06/25/2017" -Before "06/28/2017"

#Example #5 – Get only the error entries from local System log on a certain day

#Get-EventLog -LogName System -After "06/25/2017" -Before "06/28/2017" -EntryType Error

#Example #6 – Get Error and Warning Entries from local System log on a certain day

#Get-EventLog -LogName Setup -After "06/14/2017" -Before "06/15/2017" | Where-Object {$_.EntryType -like 'Information'}

#Example #7 – Get error and warning System Log entries on a certain day and order by the source

Get-EventLog -LogName System -After "06/27/2017" -Before "06/28/2017" | Where-Object {$_.EntryType -like 'Error' -or $_.EntryType -like 'Warning'} | Sort-Object Source

#Example #8 – Get error and warning entries from a remote computer on a certain day and order by the source

#Get-EventLog -ComputerName "TS" -LogName System -After "06/25/2017" -Before "06/28/2017" | Where-Object {$_.EntryType -like 'Error' -or $_.EntryType -like 'Warning'} | Sort-Object Source

#Example #9 – Get all System Log entries related to Disk

#Get-EventLog -LogName System -Source Disk

#Example #10 – Get the list of sources in local system log with the count number

#Get-EventLog -LogName System | Group-Object Source | Sort-Object Count -Descending