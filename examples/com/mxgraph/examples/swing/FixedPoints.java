package com.mxgraph.examples.swing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;

public class FixedPoints extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2707712944901661771L;

	@SuppressWarnings("unused")
	public FixedPoints()
	{
		super("Hello, World!");

		final mxGraph graph = new mxGraph();
		Object parent = graph.getDefaultParent();

		graph.getModel().beginUpdate();
		try
		{
			People p = new People();
			p.setAge("20");
			p.setName("Time");
			Object v1 = graph.insertVertex(parent, null, p, 20, 20, 80,
					60, "shape=triangle;perimeter=trianglePerimeter");
			Object v2 = graph.insertVertex(parent, null, "World!", 200, 150,
					80, 60, "shape=ellipse;perimeter=ellipsePerimeter");
			Object v3 = graph.insertVertex(parent, null, "Hello,", 200, 20, 80,
					30);
			
//			List list = new ArrayList();
//			list.add(v1);
//			list.add(v3);
//			mxCell group = (mxCell)graph.createGroupCell(list.toArray());
//			mxStylesheet stylesheet = new mxStylesheet();
//			stylesheet.getDefaultVertexStyle().put(mxConstants.STYLE_FILLCOLOR, "white");
//			graph.setStylesheet(stylesheet);
//			group.setStyle("mxConstants.STYLE_FILLCOLOR:'white'");
//			graph.groupCells(group,list.size(),list.toArray());
//			
//			List list2 = new ArrayList();
//			list2.add(v2);
//			list2.add(v3);
//			graph.groupCells(graph.createGroupCell(list2.toArray()), list2.size(), list2.toArray());
			//graph.foldCells(true);
			Object e1 = graph
					.insertEdge(
							parent,
							null,
							"",
							v1,
							v2);
			Object e2 = graph.insertEdge(parent, null, "", v3, v2,
					"edgeStyle=elbowEdgeStyle;elbow=horizontal;orthogonal=0;"
							+ "entryX=0;entryY=0;entryPerimeter=1;");
		}
		finally
		{
			graph.getModel().endUpdate();
		}

		final mxGraphComponent graphComponent = new mxGraphComponent(graph);
		getContentPane().add(graphComponent);
		
		graphComponent.getGraphControl().addMouseListener(new MouseAdapter()
		{
		
			public void mouseReleased(MouseEvent e)
			{
				mxCell cell = (mxCell)graphComponent.getCellAt(e.getX(), e.getY());
				
				if (cell != null && cell.isVertex())
				{
					System.out.println("cell="+graph.getLabel(cell));
					graph.getModel().beginUpdate();
					graph.getModel().setStyle(cell, cell.getStyle()+";fillColor=CCC333;fontColor=66FF33;gradientColor=000000;strokeColor=FF69B4;strokeWidth=5;"
							+ "opcity=30;shape=actor;glass=1");
					System.out.println(cell.getStyle());
					cell.setAttribute("2322333333333", "*********");
					graph.getModel().endUpdate();
				} 
				if(cell !=null && cell.isEdge()){
					System.out.println("cell style==="+cell.getStyle());
					graph.getModel().setStyle(cell, cell.getStyle()+";edgeStyle=rounded;strokeColor=000000;strokeWidth=5;rounded=1"
							+ ";startArrow=diamond;dashed=1");
				}
			}
		});
	}

	public static void main(String[] args)
	{
		FixedPoints frame = new FixedPoints();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 320);
		frame.setVisible(true);
	}

}
class People{
	public String name;
	public String age;
	public String getName() {
		return name;
	}
	public String getAge() {
		return age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(String age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return this.getName()+"\n-----------"+this.getAge();
		
	}
}
