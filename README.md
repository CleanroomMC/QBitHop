# QBitHop

a fork of [BitHop](https://www.curseforge.com/minecraft/mc-mods/bithop).

Improvements over BitHop:
- improve performance slightly further
- better collision boxes (similar to Diet Hopper mod)
- removes Forgelin dependency (converted all kotlin code to java)
- rewrites all UI with ModularUI (yes this adds a dependency for ModularUI) which fixes all the issues it had

The main problem with the mod is the UI issues. It was barely noticable to the user, but on every item slot click, every
slot was duplicated. Which created voiding issues for InventoryBogoSorter.

As BitHop's [license](https://github.com/elytra/BitHop/blob/master/COPYING.gpl) requires, this fork is also licensed
under the GNU General Public License.

### BitHop

BitHop is a mod to add simple, lag-free hopper alternatives. They don't pull from inventories or pick up items from the ground, so they're perfect for making long hopper chains while still staying in an easy, vanilla style!

Now included are the PullHop (pulls items from chests while skipping intensive entity checks), the FluxHop (transfers RF as well as items), and the ScrewHop (transfers items up and forward).
