#!/bin/bash

# Получаем информацию о загруженности процессора
cpu_info=$(top -n 1 -b | grep "Cpu(s)" | awk '{print $2}')

# Получаем информацию об использовании оперативной памяти
free_output=$(LC_ALL=C free)
memory_info=$(echo "$free_output" | awk '/Mem:/ {print "{\"total\":\""$2"\", \"used\":\""$3"\", \"free\":\""$4"\", \"shared\":\""$5"\", \"buff/cache\":\""$6"\"}"}')

# Собираем всю информацию в JSON
json="{\"cpuusage\":\"$cpu_info\",\"memory\":$memory_info}"

# Выводим JSON на экран
echo "$json"
