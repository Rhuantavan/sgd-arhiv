
public class Node 
{
	public int vrednost=0;
	public int sestevek=0;
	public Node left, right;
	public int bestParent = BestParentEnum.Default;

	
	public Node(int vrednost)
	{
		left = null;
		right = null;
		this.vrednost = vrednost;
	}
	
	public void izracunajSestevek()
	{
		if (right != null) 
		{ // ima dva starsa
			if (left.sestevek > right.sestevek)
			{
				bestParent = BestParentEnum.Left; // ocitno ima stars 0 boljsi sestevek
				sestevek = left.sestevek + this.vrednost;
			} 
			else if(left.sestevek < right.sestevek) 
			{
				bestParent = BestParentEnum.Right; // ocitno ima stars 1 boljsi sestevek
				sestevek = right.sestevek + this.vrednost;    
			}
			else 
			{
				bestParent = BestParentEnum.Both; // ocitno imata oba isti sestevek
				sestevek = right.sestevek + this.vrednost;        
			}
		} 
		else 
		{ // en stars
			bestParent = BestParentEnum.Left; // ce je samo en stars je ta najboljsi.
			sestevek = left.sestevek + this.vrednost;    
		}
	}
	
	public void dodajStarsa(Node n)
	{
		if (left == null)
			left = n;
		else
			right = n;
	}

}
