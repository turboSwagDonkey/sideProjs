package com.northarrow;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Perspective;
import net.runelite.api.Player;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

/**
 * Draws an arrow from the local player toward a point a few tiles north of them.
 * Both ends are real scene points projected to the screen, so the arrow follows
 * camera yaw and pitch automatically and lies flat on the ground.
 */
class NorthArrowOverlay extends Overlay
{
	// Local coordinates use 128 units per tile; north is +Y
	private static final int TILE = Perspective.LOCAL_TILE_SIZE;

	private final Client client;
	private final NorthArrowConfig config;

	@Inject
	NorthArrowOverlay(Client client, NorthArrowConfig config)
	{
		this.client = client;
		this.config = config;
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
	}

	@Override
	public Dimension render(Graphics2D g)
	{
		Player player = client.getLocalPlayer();
		if (player == null)
		{
			return null;
		}

		LocalPoint here = player.getLocalLocation();
		if (here == null)
		{
			return null;
		}
		LocalPoint north = new LocalPoint(here.getX(), here.getY() + config.distance() * TILE, here.getWorldView());
		int plane = player.getWorldLocation().getPlane();

		Point a = Perspective.localToCanvas(client, here, plane);
		Point b = Perspective.localToCanvas(client, north, plane);
		if (a == null || b == null)
		{
			return null;
		}

		double dx = b.getX() - a.getX();
		double dy = b.getY() - a.getY();
		double len = Math.hypot(dx, dy);
		if (len < 1)
		{
			// Camera looking straight down the north axis; no direction to show
			return null;
		}
		double ux = dx / len;
		double uy = dy / len;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(config.color());

		int size = config.headSize();
		// Base of the arrowhead, pulled back from the tip along the arrow direction
		double baseX = b.getX() - ux * size;
		double baseY = b.getY() - uy * size;

		if (config.showShaft())
		{
			g.setStroke(new BasicStroke(Math.max(2f, size / 5f), BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
			g.drawLine(a.getX(), a.getY(), (int) baseX, (int) baseY);
		}

		// Perpendicular to the arrow, for the two back corners of the head
		double px = -uy * size * 0.6;
		double py = ux * size * 0.6;
		Polygon head = new Polygon();
		head.addPoint(b.getX(), b.getY());
		head.addPoint((int) (baseX + px), (int) (baseY + py));
		head.addPoint((int) (baseX - px), (int) (baseY - py));
		g.fillPolygon(head);

		if (config.showLabel())
		{
			FontMetrics fm = g.getFontMetrics();
			double gap = size * 0.6 + fm.getHeight() / 2.0;
			int lx = (int) (b.getX() + ux * gap) - fm.stringWidth("N") / 2;
			int ly = (int) (b.getY() + uy * gap) + fm.getAscent() / 2;
			g.drawString("N", lx, ly);
		}

		return null;
	}
}
