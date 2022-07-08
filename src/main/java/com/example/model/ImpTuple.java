package com.example.model;

import java.util.List;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;

import com.gfc.nio.charset.MS950StringConverter;

public class ImpTuple implements Tuple{

	MS950StringConverter m = new MS950StringConverter();
	@SuppressWarnings("unchecked")
	@Override
	public <X> X get(TupleElement<X> tupleElement) {
		if(tupleElement.getJavaType()==String.class) {
			return (X) m.decode(tupleElement.toString());
		}
		return (X) tupleElement;
	}

	@Override
	public <X> X get(String alias, Class<X> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <X> X get(int i, Class<X> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TupleElement<?>> getElements() {
		// TODO Auto-generated method stub
		return null;
	}

}
