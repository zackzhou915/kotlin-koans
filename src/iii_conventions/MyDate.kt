package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year != other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }

    operator fun plus(interval: TimeInterval): MyDate {
        return addTimeIntervals(interval, 1)
    }

    operator fun plus(interval: RepeatedTimeInterval): MyDate {
        return addTimeIntervals(interval.ti, interval.n)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(n: Int) = RepeatedTimeInterval(this, n)
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(start, endInclusive)

    operator fun contains(d: MyDate): Boolean {
        return d >= start && d <= endInclusive
    }
}

class DateIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {
    private var _date = start;

    override fun next(): MyDate {
        return _date.also { _date = it.nextDay() }
    }

    override fun hasNext(): Boolean {
        return _date <= endInclusive
    }
}
