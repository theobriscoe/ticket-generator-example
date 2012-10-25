package example.model;

public class Twap {

	public String symbol = "";
	public Double twap1 = Double.valueOf(0);
	public Double twap5 = Double.valueOf(0);
	public Double twap30 = Double.valueOf(0);
	public Double twap60 = Double.valueOf(0);
	public Long timestamp = Long.valueOf(0);

	public Twap() {
	}

	public Twap(String symbol, Double twap1, Double twap5, Double twap30, Double twap60, Long timestamp) {
		super();
		this.symbol = symbol;
		this.twap1 = twap1;
		this.twap5 = twap5;
		this.twap30 = twap30;
		this.twap60 = twap60;
		this.timestamp = timestamp;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getTwap1() {
		return twap1;
	}

	public void setTwap1(Double twap1) {
		this.twap1 = twap1;
	}

	public Double getTwap5() {
		return twap5;
	}

	public void setTwap5(Double twap5) {
		this.twap5 = twap5;
	}

	public Double getTwap30() {
		return twap30;
	}

	public void setTwap30(Double twap30) {
		this.twap30 = twap30;
	}

	public Double getTwap60() {
		return twap60;
	}

	public void setTwap60(Double twap60) {
		this.twap60 = twap60;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((twap1 == null) ? 0 : twap1.hashCode());
		result = prime * result + ((twap30 == null) ? 0 : twap30.hashCode());
		result = prime * result + ((twap5 == null) ? 0 : twap5.hashCode());
		result = prime * result + ((twap60 == null) ? 0 : twap60.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Twap other = (Twap) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (twap1 == null) {
			if (other.twap1 != null)
				return false;
		} else if (!twap1.equals(other.twap1))
			return false;
		if (twap30 == null) {
			if (other.twap30 != null)
				return false;
		} else if (!twap30.equals(other.twap30))
			return false;
		if (twap5 == null) {
			if (other.twap5 != null)
				return false;
		} else if (!twap5.equals(other.twap5))
			return false;
		if (twap60 == null) {
			if (other.twap60 != null)
				return false;
		} else if (!twap60.equals(other.twap60))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Twap [symbol=" + symbol + ", twap1=" + twap1 + ", twap5=" + twap5 + ", twap30=" + twap30 + ", twap60="
				+ twap60 + ", timestamp=" + timestamp + "]";
	}
}
