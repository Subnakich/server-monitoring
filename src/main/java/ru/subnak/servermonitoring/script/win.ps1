# Получение информации о загрузке процессора
$cpuUsage = Get-WmiObject Win32_Processor | Measure-Object -Property LoadPercentage -Average | Select-Object -ExpandProperty Average

# Получение информации об оперативной памяти
$memoryInfo = Get-WmiObject Win32_OperatingSystem
$freeMemory = $memoryInfo.FreePhysicalMemory
$totalMemory = $memoryInfo.TotalVisibleMemorySize

# Вывод информации в формате JSON
$data = @{
    "cpuusage" = $cpuUsage
    "memory" = @{
        "total" = $totalMemory
        "free" = $freeMemory
        "used" = $totalMemory - $freeMemory
    }
}

$data | ConvertTo-Json
