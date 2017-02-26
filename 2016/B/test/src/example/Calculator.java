package example;

class Calculator
{
	public int add(int x, int y)
	{
		return x + y;
	}

	public int calc_linear(int n)
	{
		int result = 0;

		for(int i=0; i<n; i++)
			add(result, 1);

		return result;
	}
}
